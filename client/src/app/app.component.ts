import { Component } from '@angular/core';
import { Navigation } from '../navigation/navigation.component';

require('./../../public/scss/styles.global.scss');
require('./../../assets/favicon.ico');

@Component({
  selector: 'app',  
  templateUrl: './app.component.html',
  styles: [ require('./app.component.scss') ] 
})
export class AppComponent { }
