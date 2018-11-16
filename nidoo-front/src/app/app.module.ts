import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ParkingLotsComponent } from './components/parking-lots/parking-lots.component';

import { LoginComponent } from './components/login/login.component';
import { NewReservationComponent } from './components/new-reservation/new-reservation.component';
import { AppRoutingModule } from './app.routing';
import { AuthenticationService } from './services/authentication/authentication.service';
import { ParkingLotsService } from './services/parking-services/parking-lots.service';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavbarComponent,
    ParkingLotsComponent,
    LoginComponent,
    NewReservationComponent,
    RegisterComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBB8JU8u2EpGIGKobetFAoyTUtihffVKMU'
    })
  ],
  providers: [ParkingLotsService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {}
