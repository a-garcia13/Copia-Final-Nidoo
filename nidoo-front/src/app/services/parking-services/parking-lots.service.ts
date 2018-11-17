import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { map } from 'rxjs/operators';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ParkingLotsService {
  options;

  constructor(
    private http: Http,
    private authentication: AuthenticationService
  ) {}

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

  makeAReservation(reservation) {
    return this.authentication
      .getAuthenticatedUser()
      .getSession((err, session) => {
        if (err) {
          alert(err);
          return;
        }
        const idToken = session.getIdToken();
        const token = idToken.getJwtToken();

        this.options = new RequestOptions({
          headers: new Headers({
            'Content-Type': 'application/json',
            Authorization: token
          })
        });
        reservation.user = idToken.payload.email;
        return this.http
          .post(
            'https://sfz7itr5a1.execute-api.us-east-1.amazonaws.com/prod/parqueaderos',
            reservation,
            this.options
          )
          .pipe(map(res => res.json()));
      });
  }
}
