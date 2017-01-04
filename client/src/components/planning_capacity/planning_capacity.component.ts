import { PlanningService } from '../planning/planning.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'planning_capacity',
    templateUrl: './planning_capacity.component.html',
    styleUrls: ['./planning_capacity.component.scss']
})
export class PlanningCapacityComponent implements OnInit {
    workplaces: any[];

    constructor(private planningService: PlanningService) {
    }

    ngOnInit(): void {
        this.workplaces = this.planningService.inputDataForSimulatorAsJSON.workingtimelist.workingtime;
    }
}
