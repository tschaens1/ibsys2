import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
    selector: 'planning_overview',
    templateUrl: './planning_overview.component.html',
    styleUrls: ['./planning_overview.component.scss'] 
})
export class PlanningOverviewComponent{     
    constructor(private router: Router){

    }


    loadComponent(site: string){
        this.router.navigate(['app/planning/'+site]);
    }
}
