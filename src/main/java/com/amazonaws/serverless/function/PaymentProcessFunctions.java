package com.amazonaws.serverless.function;

import com.amazonaws.serverless.dao.DynamoDBPagoDao;
import com.amazonaws.serverless.domain.Pago;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.MessageAttribute;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;

import org.apache.log4j.Logger;

import java.util.Map;

public class PaymentProcessFunctions implements RequestHandler<SQSEvent, Void>
{

    private static final DynamoDBPagoDao pagoDao = DynamoDBPagoDao.instance();

    @Override
    public Void handleRequest(SQSEvent event, Context context)
    {
        context.getLogger().log("Inicio de procesamiento de pagos");
        for(SQSMessage msg : event.getRecords())
        {
            context.getLogger().log(String.valueOf(msg.getAttributes()));
            context.getLogger().log(String.valueOf(msg.getMessageAttributes()));
            Map<String, MessageAttribute> currentMessage = msg.getMessageAttributes();

            MessageAttribute fecha = currentMessage.get("fecha");
            MessageAttribute tarjetaHabiente = currentMessage.get("tarjetaHabiente");
            MessageAttribute codigoSeguridad = currentMessage.get("codigoSeguridad");
            MessageAttribute numeroTarjeta = currentMessage.get("numeroTarjeta");
            MessageAttribute valor = currentMessage.get("valor");
            MessageAttribute correo = currentMessage.get("correo");
            Pago pago = new Pago(tarjetaHabiente.getStringValue(), fecha.getStringValue(), Double.valueOf(valor.getStringValue()), correo.getStringValue(), codigoSeguridad.getStringValue(), numeroTarjeta.getStringValue());
            saveOrUpdatePago(pago, context);
        }
        context.getLogger().log("Fin de procesamiento de pagos");
        return null;
    }

    private void saveOrUpdatePago(Pago pago,  Context context)
    {

        if (null == pago)
        {
            context.getLogger().log("Salvar o actualizar pago  ha recibido un parámetro de entrada nulo");
            throw new IllegalArgumentException("No se puede salvar un objeto nulo");
        }

        context.getLogger().log("Salvando o actualizando un pago para: " + pago.getTarjetaHabiente());
        pagoDao.saveOrUpdatePago(pago);
        context.getLogger().log("Pago salvado/actualizado satisfactoriamente");
    }
}
