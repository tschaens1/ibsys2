import { Component } from '@angular/core';
import { Navigation } from '../navigation/navigation.component';
let styles = String(require('./warehouse.component.css'));

@Component({
    selector: 'warehouse',
    templateUrl: './warehouse.component.html',
    styleUrls: [styles]
})
export class WarehouseComponent { }