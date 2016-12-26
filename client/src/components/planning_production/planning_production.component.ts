import { PlanningService } from '../planning/planning.service';
import { Component } from '@angular/core';

@Component({
    selector: 'planning_production',
    templateUrl: './planning_production.component.html',
    styleUrls: ['./planning_production.component.scss']
})
export class PlanningProductionComponent {
    resultsJSON: any;
    constructor(private planningService: PlanningService) { }

    ngOnInit(): void {
        console.log(this.planningService.inputDataForSimulatorAsJSON);
        this.resultsJSON = this.planningService.inputDataForSimulatorAsJSON;
    }

    onChange(input: HTMLInputElement) {
        this.planningService.updateInputJSON(input.value);
    }
}
