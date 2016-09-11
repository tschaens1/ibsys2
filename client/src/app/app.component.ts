import { Component } from '@angular/core';
import { Navigation } from '../navigation/navigation.component';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styles: [ require('./app.component.scss') ] 
})
export class AppComponent {
    counter = 0;
    increment(){
        this.counter += 3;
    }
 }
