import { Component, OnInit } from '@angular/core';
import { ParkingLotsService } from '../../services/parking-services/parking-lots.service';

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
  public nearParkingLots = [];

  constructor(private parkingLotsService: ParkingLotsService) {}

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
