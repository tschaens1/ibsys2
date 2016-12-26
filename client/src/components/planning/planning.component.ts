import { ToastsManager } from 'ng2-toastr';
import { TranslationService } from '../translate/translate.service';
import { ModalService } from '../modal/modal.service';
import { Router } from '@angular/router';
import { Component, AfterViewInit } from '@angular/core';
import { PlanningService } from './planning.service';

@Component({
    selector: 'planning',
    templateUrl: './planning.component.html',
    styleUrls: ['./planning.component.scss']
})
export class PlanningComponent {
    constructor(
        private planningService: PlanningService,
        private router: Router,
        private modal: ModalService,
        private translationService: TranslationService,
        private toastr: ToastsManager) { }

    saveInputs() {
        this.planningService.generateInputXMLForSimulator().then(() => {
            this.toastr.success(this.translationService.instant('planning.toasts.savedXMLSuccessfully'));
        }).catch(err => {
            this.toastr.error(this.translationService.instant('planning.toasts.errorWhileSavingXML'));
        });
    }

    cancelPlanning() {
        this.modal.openModal(this.translationService.instant('planning.modals.exitPlanningText'), this.translationService.instant('planning.modals.exitPlanningTitle'), false, () => {
            this.planningService.startedPlanning = false;
            this.router.navigate(['/']);
        });
    }
}
