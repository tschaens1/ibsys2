import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { Navigation } from '../navigation/navigation.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login/login.service';
import { LoginGuard } from '../login/login.guard';
import { LogoutGuard } from '../login/logout.guard';
import { StartComponent } from '../start/start.component';

import { routing } from './app.routing';

@NgModule({
  imports: [
    BrowserModule,
    routing
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    StartComponent,
    WarehouseComponent,
    Navigation
  ],
  providers: [
    LoginService,
    LoginGuard,
    LogoutGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
