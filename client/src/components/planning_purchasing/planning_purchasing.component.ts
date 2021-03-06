import { Subscription } from 'rxjs/Rx';
import { ToastsManager } from 'ng2-toastr';
import { ModalService } from '../modal/modal.service';
import { Part } from '../parts/part.interface';
import { TranslationService } from '../translate/translate.service';
import { PlanningService } from '../planning/planning.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { DragulaService } from 'ng2-dragula';
import { basic_claims_data_DE } from './../translate/basic_claims_data_de';
import { basic_claims_data_EN } from './../translate/basic_claims_data_en';
import * as _ from 'lodash';

@Component({
    selector: 'planning_purchasing',
    templateUrl: './planning_purchasing.component.html',
    styleUrls: ['./planning_purchasing.component.scss']
})
export class PlanningPurchasingComponent implements OnInit, OnDestroy {
    parts: any[];
    data: Part[];

    dragulaSubscription: Subscription;

    constructor(
        private planningService: PlanningService,
        private dragulaService: DragulaService,
        private translationService: TranslationService,
        private toastr: ToastsManager,
        private modal: ModalService,
    ) {
        const bag: any = this.dragulaService.find('purchasing-bag');
        if (bag !== undefined) this.dragulaService.destroy('purchasing-bag');

        dragulaService.setOptions('purchasing-bag', {
            removeOnSpill: true,
        });

        this.dragulaSubscription = dragulaService.removeModel.subscribe((value) => {
            this.onRemoveModel(value.slice(1));
        });
    }

    onRemoveModel(args) {
        const [el, source] = args;
        const delArticleId = $(el).find('.detail').first().html();
        // this.toastr.info(this.translationService.instant('planning_material.modal.removedArticle', delArticleId));
    }

    ngOnInit(): void {
        this.parts = this.planningService.inputDataForSimulatorAsJSON.orderlist.order;
        this.data = this.translationService.currentLanguage === 'de' ? basic_claims_data_DE : basic_claims_data_EN;
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
        if (this.planningService.orderlist.filter((orderId: number) => orderId.toString() === articleId).length === 0) {
            this.toastr.error(this.translationService.instant('planning_material.validation.invalid_article_id'));
            return;
        }
        this.planningService.inputDataForSimulatorAsJSON.orderlist.order.unshift(
            {
                "@": {
                    "article": articleId,
                    "quantity": 0,
                    "modus": 5,
                }
            }
        );
        // this.toastr.info(this.translationService.instant('planning_material.modal.addedArticle', id.value));
        id.value = '';
    }

    openHelp(key: string) {
        this.modal.openModal(this.translationService.instant(key), this.translationService.instant('modal.title.help'));
    }

    ngOnDestroy(): void {
        this.dragulaSubscription.unsubscribe();
    }
}
