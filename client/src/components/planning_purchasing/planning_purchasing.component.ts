import { PlanningService } from '../planning/planning.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'planning_purchasing',
    templateUrl: './planning_purchasing.component.html',
    styleUrls: ['./planning_purchasing.component.scss']
})
export class PlanningPurchasingComponent implements OnInit {
    resultsFromServer: any;
    constructor(private planningService: PlanningService) { }

    ngOnInit(): void {
        this.planningService.resultJSON.subscribe(results => {
            this.resultsFromServer = results;
        });
    }

    onChange(input: HTMLInputElement){
        
    }
}