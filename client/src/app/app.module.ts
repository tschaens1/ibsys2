import { NgModule } from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { Navigation } from '../navigation/navigation.component';
import { StartComponent } from '../start/start.component';

import { routing } from './app.routing';

@NgModule({
  imports: [
    BrowserModule,
    routing
  ],
  declarations: [
    AppComponent,
    StartComponent,
    Navigation
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
