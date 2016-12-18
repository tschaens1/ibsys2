import { Router } from '@angular/router';
import { Component, AfterViewInit } from '@angular/core';
import { PlanningService } from './planning.service';

@Component({
    selector: 'planning',
    templateUrl: './planning.component.html',
    styleUrls: ['./planning.component.scss']
})
export class PlanningComponent {
    constructor(private planningService: PlanningService, private router: Router) { }

    saveInputs() {
        this.planningService.generateInputXMLForSimulator();
    }

    cancelPlanning() {
        if (confirm('Do you really want to exit the planning?')) {
            this.planningService.startedPlanning = false;
            this.router.navigate(['/']);
        }
    }
}
