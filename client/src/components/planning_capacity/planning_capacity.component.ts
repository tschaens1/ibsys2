import { TranslationService } from '../translate/translate.service';
import { ModalService } from '../modal/modal.service';
import { PlanningService } from '../planning/planning.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'planning_capacity',
    templateUrl: './planning_capacity.component.html',
    styleUrls: ['./planning_capacity.component.scss']
})
export class PlanningCapacityComponent implements OnInit {
    workplaces: any[];

    constructor(
        private planningService: PlanningService,
        private modal: ModalService,
        private translationService: TranslationService,
    ) {
    }

    ngOnInit(): void {
        this.workplaces = this.planningService.inputDataForSimulatorAsJSON.workingtimelist.workingtime;
    }

    openHelp(key: string) {
        this.modal.openModal(this.translationService.instant(key), this.translationService.instant('modal.title.help'));
    }
}
