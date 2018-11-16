import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(public authentication: AuthenticationService) {
    this.loggedIn = authentication.isAuthenticated();
  }

  loggedIn: boolean;

  logOut() {
    if (this.loggedIn) {
      this.authentication.logOut();
    } else {
      window.location.reload();
    }
  }

  ngOnInit() {
    this.loggedIn = this.authentication.isAuthenticated();
  }
}
