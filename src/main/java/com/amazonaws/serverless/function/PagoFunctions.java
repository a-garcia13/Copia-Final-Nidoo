package com.amazonaws.serverless.function;

import com.amazonaws.serverless.pojo.Pago;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class PagoFunctions {

    private static final Logger log = Logger.getLogger(PagoFunctions.class);
    private final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    private final String queueUrl =  System.getenv("queueUrl");


    public void sendPago(Pago pago)
    {
        if(null == pago) {
            log.error("sendPago ha recibido un pago nulo");
            throw new IllegalArgumentException("No se puede enviar un objeto nulo");
        }

        if(pago.getCodigoSeguridad() == 0 || pago.getFecha() == null || pago.getNumeroTarjeta() == null ||
            pago.getTarjetaHabiente() == null || pago.getValor() == 0 || pago.getCorreo() == null) {
            log.error("sendPago ha recibido un parametros nulos");
            throw new IllegalArgumentException("No se puede enviar un pago con parámetros nulos");
        }

        final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();

        messageAttributes.put("tarjetaHabiente", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue(pago.getTarjetaHabiente()));

        messageAttributes.put("codigoSeguridad", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue((Integer.toString(pago.getCodigoSeguridad()))));

        messageAttributes.put("numeroTarjeta", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue((Long.toString(pago.getNumeroTarjeta()))));

        messageAttributes.put("valor", new MessageAttributeValue()
                .withDataType("Number")
                .withStringValue((Float.toString(pago.getValor()))));

        messageAttributes.put("fecha", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue((pago.getFecha().toString())));

        messageAttributes.put("correo", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue((pago.getCorreo())));

        final SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.withMessageBody("New page from Nido.");
        sendMessageRequest.withQueueUrl(queueUrl);
        sendMessageRequest.withMessageAttributes(messageAttributes);

        sqs.sendMessage(sendMessageRequest);
        log.info("Mensaje enviado a la cola SQS. Esperando procesamiento asincrónico");
    }
}
