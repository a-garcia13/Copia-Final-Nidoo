import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParkingLotsComponent } from './components/parking-lots/parking-lots.component';
import { LoginComponent } from './components/login/login.component';
import { AuthenticatedGuardService } from './services/guards/authenticated-guard.service';
import { NotAuthenticatedGuardService } from './services/guards/not-authenticated-guard.service';
import { AuthenticationService } from './services/authentication/authentication.service';
import { RegisterComponent } from './components/register/register.component';

const appRoutes: Routes = [
  {
    path: '',
    component: ParkingLotsComponent
  },
  {
    path: 'iniciarSesion',
    component: LoginComponent,
    canActivate: [NotAuthenticatedGuardService]
  },
  {
    path: 'registro',
    component: RegisterComponent,
    canActivate: [NotAuthenticatedGuardService]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(appRoutes, { scrollPositionRestoration: 'enabled' })
  ],
  providers: [],
  bootstrap: [],
  exports: [RouterModule]
})
export class AppRoutingModule {}
