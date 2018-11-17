import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ParkingLotsService {
  options;

  constructor(private http: Http) {}

  createHeaders() {
    this.options = new RequestOptions({
      headers: new Headers({
        'Content-Type': 'application/json'
      })
    });
  }

  getNearParkingLots(coordinates) {
    this.createHeaders();
    return this.http
      .post(
        'https://api.nidoo.tk/park-search/parqueaderos',
        coordinates,
        this.options
      )
      .pipe(map(res => res.json()));
  }
}
