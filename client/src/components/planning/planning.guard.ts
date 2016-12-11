import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { PlanningService } from './planning.service';

@Injectable()
export class PlanningGuard implements CanActivate {
    constructor(private planningService: PlanningService, private router: Router) { }

    canActivate() {
        if (this.planningService.startedPlanning !== true) {
            this.router.navigate(['/app/planning']);
            return false;
        } else {
            return true;
        }
    }
}