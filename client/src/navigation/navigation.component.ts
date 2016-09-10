import { Component } from '@angular/core';
let styles = String(require('./navigation.component.css'));

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styles: [styles]  
})
export class Navigation { }
