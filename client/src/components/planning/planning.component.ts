import { Component, AfterViewInit } from '@angular/core';
import { PlanningService } from './planning.service';

@Component({
    selector: 'planning',
    templateUrl: './planning.component.html',
    styleUrls: ['./planning.component.scss']
})
export class PlanningComponent {
    constructor(private planningService: PlanningService) { }

    saveInputs(){
        this.planningService.generateInputXMLForSimulator();
    }
}
