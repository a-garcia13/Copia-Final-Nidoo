import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ParkingLotsComponent } from './components/parking-lots/parking-lots.component';

import { ParkingLotsService } from './services/parking-services/parking-lots.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavbarComponent,
    ParkingLotsComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBB8JU8u2EpGIGKobetFAoyTUtihffVKMU'
    })
  ],
  providers: [ParkingLotsService],
  bootstrap: [AppComponent]
})
export class AppModule {}
