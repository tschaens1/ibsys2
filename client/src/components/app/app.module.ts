import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login/login.service';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { PageNotFoundComponent } from './../pagenotfound/pagenotfound.component';
import { StartComponent } from '../start/start.component';
import { DashBoardComponent } from '../dashboard/dashboard.component';

import { routing } from './app.routing';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import { MaterialModule } from '@angular/material';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,    
    routing,
    ChartsModule,
    MaterialModule.forRoot(),
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    StartComponent,
    WarehouseComponent,  
    DashBoardComponent,  
    PageNotFoundComponent
  ],
  providers: [
    LoginService,
    LoggedInGuard,
    LoggedOutGuard
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
