import { Component, OnInit } from '@angular/core';
import { ParkingLotsService } from '../../services/parking-services/parking-lots.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { Router } from '@angular/router';
import { NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-parking-lots',
  templateUrl: './parking-lots.component.html',
  styleUrls: ['./parking-lots.component.css']
})
export class ParkingLotsComponent implements OnInit {
  public coordinates = {
    coordenadaX: '',
    coordenadaY: ''
  };

  time: NgbTimeStruct;

  minutes: number;
  hour: number;

  user: any;

  public nearParkingLots = [];

  public selectedParking: any;

  constructor(
    private parkingLotsService: ParkingLotsService,
    private authentication: AuthenticationService,
    private router: Router
  ) {}

  loadTime() {
    const currentTime = new Date();
    this.minutes = currentTime.getMinutes();
    this.hour = currentTime.getHours();
    this.time = {
      hour: this.hour,
      minute: this.minutes,
      second: 0
    };
  }

  makeAReservation(parking) {
    if (this.authentication.isAuthenticated()) {
      this.loadTime();
      this.selectedParking = parking;
      document.getElementById('parkModal').click();
    } else {
      alert('Debes iniciar sesión para reservar un parqueadero.');
      this.router.navigate(['iniciarSesion']);
    }
  }

  reserveParking() {
    const selectedMinutes = this.time.minute;
    const selectedHour = this.time.hour;
    if (selectedHour < this.hour) {
      alert('La hora seleccionada no puede ser menor a la actual.');
      return;
    } else {
      if (this.hour === selectedHour && selectedMinutes - 5 < this.minutes) {
        alert(
          'Se requieren al menos 5 minutos de anticipación para realizar una reserva.'
        );
        return;
      } else {
        const user = this.authentication.getSession();
        this.authentication.getUserAttributes(user).then(data => {
          const reservation = {
            user: data[2].Value,
            parking: this.selectedParking.nombreLugar,
            hour: selectedHour,
            minute: selectedMinutes
          };
          alert(
            'La reserva ha sido realizada de forma exitosa a las ' +
              selectedHour +
              ':' +
              selectedMinutes
          );
          document.getElementById('closeModal').click();
        });
      }
    }
  }

  getNearParkingLots(position) {
    this.coordinates = {
      coordenadaX: position.coords.latitude,
      coordenadaY: position.coords.longitude
    };

    this.parkingLotsService
      .getNearParkingLots(this.coordinates)
      .subscribe(data => {
        this.nearParkingLots = data;
      });
  }

  ngOnInit() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        this.getNearParkingLots(position);
      });
    } else {
      alert('La geolocalización no es soportada por el navegador.');
    }
  }
}
