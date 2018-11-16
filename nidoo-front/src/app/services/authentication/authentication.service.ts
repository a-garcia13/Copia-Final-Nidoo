import { Injectable } from '@angular/core';
// import {
//   AuthenticationDetails,
//   CognitoUser,
//   CognitoUserPool
// } from 'amazon-cognito-identity-js';
// import { Observable } from 'rxjs';

const poolData = {
  UserPoolId: 'us-east-1_bZF4aD0pr', // Your user pool id here
  ClientId: '60rjtu8uhhme82ruj6h342ofkn' // Your client id here
};

// const userPool = new CognitoUserPool(poolData);

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  cognitoUser: any;

  constructor() {}

  logIn(email, password) {
    alert(email + password);
    //   const authenticationData = {
    //     Username: email,
    //     Password: password
    //   };
    //   const authenticationDetails = new AuthenticationDetails(authenticationData);
    //   const userData = {
    //     Username: email,
    //     Pool: userPool
    //   };
    //   const cognitoUser = new CognitoUser(userData);
    //   return Observable.create(observer => {
    //     cognitoUser.authenticateUser(authenticationDetails, {
    //       onSuccess: function(result) {
    //         // console.log(result);
    //         observer.next(result);
    //         observer.complete();
    //       },
    //       onFailure: function(err) {
    //         console.log(err);
    //         observer.error(err);
    //       }
    //     });
    //   });
  }

  // isLoggedIn() {
  //   return userPool.getCurrentUser() != null;
  // }

  // getAuthenticatedUser() {
  //   // gets the current user from the local storage
  //   return userPool.getCurrentUser();
  // }

  // logOut() {
  //   this.getAuthenticatedUser().signOut();
  //   this.cognitoUser = null;
  // }
}
