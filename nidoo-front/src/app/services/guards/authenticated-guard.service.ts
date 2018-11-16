import { Injectable } from '@angular/core';
import { Router, CanActivate, CanLoad } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticatedGuardService implements CanActivate {
  constructor(
    public authentication: AuthenticationService,
    public router: Router
  ) {}

  canActivate(): boolean {
    if (!this.authentication.isAuthenticated()) {
      this.router.navigate(['iniciarSesion']);
      return false;
    }

    return true;
  }
}
