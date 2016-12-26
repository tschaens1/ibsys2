import { ToastsManager } from 'ng2-toastr';
import { ModalService } from '../modal/modal.service';
import { Part } from '../parts/part.interface';
import { TranslationService } from '../translate/translate.service';
import { PlanningService } from '../planning/planning.service';
import { Component, OnInit } from '@angular/core';
import { DragulaService } from 'ng2-dragula';
import { basic_claims_data_DE } from './../translate/basic_claims_data_de';
import { basic_claims_data_EN } from './../translate/basic_claims_data_en';
import * as _ from 'lodash';

@Component({
    selector: 'planning_material',
    templateUrl: './planning_material.component.html',
    styleUrls: ['./planning_material.component.scss']
})
export class PlanningMaterialComponent implements OnInit {
    parts: any[];
    data: Part[];
    isDragging: boolean = false;

    constructor(
        private planningService: PlanningService,
        private dragulaService: DragulaService,
        private translationService: TranslationService,
        private toastr: ToastsManager,
        private modal: ModalService,
    ) {
    }

    ngOnInit(): void {
        this.parts = this.planningService.inputDataForSimulatorAsJSON.productionlist.production;
        this.data = this.translationService.currentLanguage === 'de' ? basic_claims_data_DE : basic_claims_data_EN;

        this.dragulaService.drag.subscribe(() => {
            this.isDragging = true;
            console.log(this.isDragging);
        });

        this.dragulaService.dragend.subscribe(() => {
            this.isDragging = false;
            console.log(this.isDragging);
        });
    }

    getPartInformation(id: string) {
        let article = this.data.filter(entry => entry.id.indexOf(id) === 0)[0];
        return article;
    }

    getWorkstations(id: string): string[] {
        let idsOfWorkStations: string[];

        for (let z = 0; z < this.data.length; z++) {
            if (this.data[z].id === id) {
                let keys = Object.keys(this.data[z]).filter(key => {
                    return (key.indexOf('ProcessingTimeWS') !== -1 && _.get(this.data[z], key) > 0)
                }).map(key => {
                    return key.slice(key.indexOf('WS') + 2);
                });
                idsOfWorkStations = keys;
                break;
            }
        }

        return idsOfWorkStations;
    }

    addPart(id: HTMLInputElement) {
        let articleId = id.value;
        if (this.planningService.productionlist.filter((p: number) => p.toString() === articleId).length === 0) {
            this.toastr.error('Invalid article id');
            return;
        }
        this.planningService.inputDataForSimulatorAsJSON.productionlist.production.push(
            {
                "@": {
                    "article": articleId,
                    "quantity": 0,
                }
            }
        );
        id.value = '';
    }

    openHelp(key: string) {
        this.modal.openModal(this.translationService.instant(key), this.translationService.instant('modal.title.help'));
    }
}
