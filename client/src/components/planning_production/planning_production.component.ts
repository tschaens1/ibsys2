import { Component } from '@angular/core';

@Component({
    selector: 'planning_production',
    templateUrl: './planning_production.component.html',
    styleUrls: ['./planning_production.component.scss'] 
})
export class PlanningProductionComponent{     
    constructor(){
    }

    saveInputs(event){
        event.stopPropagation();
        event.preventDefault();
        alert('saved');
    }
}
