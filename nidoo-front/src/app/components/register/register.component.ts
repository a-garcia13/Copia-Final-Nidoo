import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(
    private fB: FormBuilder,
    private router: Router,
    private authentication: AuthenticationService
  ) {
    this.createRegisterForm();
  }

  registerForm: FormGroup;

  createRegisterForm() {
    this.registerForm = this.fB.group({
      email: [''],
      password: ['']
    });
  }

  onRegisterSubmit() {
    const email = this.registerForm.value.email;
    const password = this.registerForm.value.password;
    this.authentication.register(email, password);
  }

  ngOnInit() {}
}
