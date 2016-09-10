import { Component } from '@angular/core';
import { Navigation } from '../navigation/navigation.component';
let styles = String(require('./start.component.css'));

@Component({
    selector: 'start',
    templateUrl: './start.component.html',
    styleUrls: [styles]
})
export class StartComponent { }