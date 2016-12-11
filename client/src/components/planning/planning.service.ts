import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class PlanningService {
    startedPlanning: boolean;
    isLoading: boolean;

    period: Number;

    constructor(private router: Router) {
        this.startedPlanning = false;
        this.isLoading = false;
    }

    startPlanning() {
        this.startedPlanning = true;
        // request an den server senden!!! -> HIER
        
    }

}