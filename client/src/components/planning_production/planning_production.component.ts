import { PlanningService } from '../planning/planning.service';
import { Component } from '@angular/core';

@Component({
    selector: 'planning_production',
    templateUrl: './planning_production.component.html',
    styleUrls: ['./planning_production.component.scss']
})
export class PlanningProductionComponent {
    resultsJSON: any;
    inputJSON: any;
    constructor(private planningService: PlanningService) { }

    ngOnInit(): void {
        this.resultsJSON = this.planningService.inputDataForSimulatorAsJSON;
        this.inputJSON = this.planningService.inputJSON;
    }

    onChange(input: HTMLInputElement) {
        this.planningService.updateInputJSON(input.value);
    }
}
