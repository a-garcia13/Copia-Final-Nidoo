import { Injectable } from '@angular/core';
import {
  AuthenticationDetails,
  CognitoUser,
  CognitoUserAttribute,
  CognitoUserPool
} from 'amazon-cognito-identity-js';
import { Router } from '@angular/router';

const poolData = {
  UserPoolId: 'us-east-1_oD1hx6lOO', // Your user pool id here
  ClientId: '2g7rf0irc5fqhbcsmhrmct87ub' // Your client id here
};

const userPool = new CognitoUserPool(poolData);

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  cognitoUser: any;
  accessToken;

  constructor(public router: Router) {}

  logIn(email, password) {
    const authenticationData = {
      Username: email,
      Password: password
    };

    const authenticationDetails = new AuthenticationDetails(authenticationData);

    const userData = {
      Username: email,
      Pool: userPool
    };

    const cognitoUser = new CognitoUser(userData);

    cognitoUser.authenticateUser(authenticationDetails, {
      onSuccess: result => {
        // this.accessToken = result.getAccessToken().getJwtToken();
        window.location.reload();
      },
      onFailure: err => {
        alert('Error: ' + err.message);
      }
    });
  }

  register(email, password) {
    const attributeList = [];

    userPool.signUp(email, password, attributeList, null, (err, result) => {
      if (err) {
        alert('Se presentó un error en el registro');
        return;
      }
      this.cognitoUser = result.user;
      alert('Registro exitoso. Verifica tu email.');
      this.router.navigate(['/iniciarSesion']);
    });
  }

  getAuthenticatedUser() {
    return userPool.getCurrentUser();
  }

  logOut() {
    if (this.getAuthenticatedUser()) {
      this.getAuthenticatedUser().signOut();
    }
    this.cognitoUser = null;
    window.location.reload();
  }

  isAuthenticated() {
    return userPool.getCurrentUser() != null;
  }
}
