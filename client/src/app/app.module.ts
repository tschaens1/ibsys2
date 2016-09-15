import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { Navigation } from '../navigation/navigation.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login/login.service';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { StartComponent } from '../start/start.component';

import { routing } from './app.routing';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,    
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
    LoggedInGuard,
    LoggedOutGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
