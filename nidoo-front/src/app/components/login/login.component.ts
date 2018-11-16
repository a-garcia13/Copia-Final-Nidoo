import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(
    private fB: FormBuilder,
    private router: Router,
    private authentication: AuthenticationService
  ) {
    this.createLoginForm();
  }

  loginForm: FormGroup;

  createLoginForm() {
    this.loginForm = this.fB.group({
      email: [''],
      password: ['']
    });
  }

  onLoginSubmit() {
    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;
    this.authentication.logIn(email, password).subscribe(
      data => {
        this.router.navigate(['/realizarReserva']);
      },
      err => {
        alert(err);
      }
    );
  }

  ngOnInit() {}
}
