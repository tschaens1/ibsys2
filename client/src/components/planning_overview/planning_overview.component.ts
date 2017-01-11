import { basic_claims_data_EN } from '../translate/basic_claims_data_en';
import { basic_claims_data_DE } from '../translate/basic_claims_data_de';
import { Part } from '../parts/part.interface';
import { ModalService } from '../modal/modal.service';
import { TranslationService } from '../translate/translate.service';
import { Component, OnInit, ElementRef, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Headers, Http, Response } from '@angular/http';
import { PlanningService } from './../planning/planning.service';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';

@Component({
    selector: 'planning_overview',
    templateUrl: './planning_overview.component.html',
    styleUrls: ['./planning_overview.component.scss']
})
export class PlanningOverviewComponent implements OnInit {
    page: number = 0;
    xmlObject: any;
    inputOflastPeriod: any;

    // part data
    data: Part[];

    // data
    // period: number;
    sellwish_p1: number;
    sellwish_p2: number;
    sellwish_p3: number;

    forecast_p1_one: number;
    forecast_p1_two: number;
    forecast_p1_three: number;

    forecast_p2_one: number;
    forecast_p2_two: number;
    forecast_p2_three: number;

    forecast_p3_one: number;
    forecast_p3_two: number;
    forecast_p3_three: number;

    enabledSellDirect: boolean = false;

    selldirect_price_p1: string;
    selldirect_price_p2: string;
    selldirect_price_p3: string;

    selldirect_quantity_p1: number;
    selldirect_quantity_p2: number;
    selldirect_quantity_p3: number;

    selldirect_penalty_p1: string;
    selldirect_penalty_p2: string;
    selldirect_penalty_p3: string;

    // enabledPartsSafetyStock: boolean = false;
    safetyStock = [];
    productionParts = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 26, 29, 30, 31, 49, 50, 51, 54, 55, 56];
    defaultSafetyStock = [60, 60, 60, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 70, 70, 50, 50, 50, 70, 50, 50, 50, 50, 50, 50, 50, 50, 50];
    safetyCategory = ["B", "B", "B", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "A", "A", "C", "C", "C", "A", "C", "C", "C", "C", "C", "C", "C", "C", "C"];

    constructor(
        private router: Router,
        private planningService: PlanningService,
        private toastr: ToastsManager,
        private route: ActivatedRoute,
        private http: Http,
        private el: ElementRef,
        private translationService: TranslationService,
        private modal: ModalService,
        @Inject('ApiEndpoint') private apiEndpoint
    ) { }

    ngOnInit() {
        // create safety stock objects
        this.productionParts.map((part, i) => {
            this.safetyStock.push({
                article: part,
                quantity: this.defaultSafetyStock[i],
                category: this.safetyCategory[i],
            });
        });

        // this.period = this.planningService.currentPeriod;

        this.loadInputsOfLastPeriod();

        this.data = this.translationService.currentLanguage === 'de' ? basic_claims_data_DE : basic_claims_data_EN;
    }

    getPartInformation(id: string) {
        let article = this.data.filter(entry => entry.id.indexOf(id) === 0)[0];
        return article;
    }

    // load input of last period
    loadInputsOfLastPeriod() {

        // load data from local storage
        this.inputOflastPeriod = JSON.parse(localStorage.getItem("ibsys2InputLastPeriod"));

        if (!this.inputOflastPeriod) {
            return;
        }

        try {
            // this.planningService.currentPeriod = this.inputOflastPeriod.results.period || 1;

            this.sellwish_p1 = this.inputOflastPeriod.input.sellwish.items[0].quantity || 0;
            this.sellwish_p2 = this.inputOflastPeriod.input.sellwish.items[1].quantity || 0;
            this.sellwish_p3 = this.inputOflastPeriod.input.sellwish.items[2].quantity || 0;

            this.forecast_p1_one = this.inputOflastPeriod.input.forecasts.forecast_one.items[0].quantity || 0;
            this.forecast_p1_two = this.inputOflastPeriod.input.forecasts.forecast_one.items[0].quantity || 0;
            this.forecast_p1_three = this.inputOflastPeriod.input.forecasts.forecast_one.items[0].quantity || 0;

            this.forecast_p2_one = this.inputOflastPeriod.input.forecasts.forecast_one.items[1].quantity || 0;
            this.forecast_p2_two = this.inputOflastPeriod.input.forecasts.forecast_one.items[1].quantity || 0;
            this.forecast_p2_three = this.inputOflastPeriod.input.forecasts.forecast_one.items[1].quantity || 0;

            this.forecast_p3_one = this.inputOflastPeriod.input.forecasts.forecast_one.items[2].quantity || 0;
            this.forecast_p3_two = this.inputOflastPeriod.input.forecasts.forecast_one.items[2].quantity || 0;
            this.forecast_p3_three = this.inputOflastPeriod.input.forecasts.forecast_one.items[2].quantity || 0;

            this.enabledSellDirect = this.inputOflastPeriod.enabledSellDirect || false;

            this.selldirect_price_p1 = this.inputOflastPeriod.input.selldirect.items[0].price || "0";
            this.selldirect_price_p2 = this.inputOflastPeriod.input.selldirect.items[1].price || "0";
            this.selldirect_price_p3 = this.inputOflastPeriod.input.selldirect.items[2].price || "0";

            this.selldirect_quantity_p1 = this.inputOflastPeriod.input.selldirect.items[0].quantity || 0;
            this.selldirect_quantity_p2 = this.inputOflastPeriod.input.selldirect.items[1].quantity || 0;
            this.selldirect_quantity_p3 = this.inputOflastPeriod.input.selldirect.items[2].quantity || 0;

            this.selldirect_penalty_p1 = this.inputOflastPeriod.input.selldirect.items[0].penalty || "0";
            this.selldirect_penalty_p2 = this.inputOflastPeriod.input.selldirect.items[1].penalty || "0";
            this.selldirect_penalty_p3 = this.inputOflastPeriod.input.selldirect.items[2].penalty || "0";
        }
        catch (err) {
            console.log('Could not load inputs of last period!', err);
            this.clearInputs();
        }
    }

    clearInputs() {
        this.planningService.currentPeriod = 0;

        this.sellwish_p1 = 0;
        this.sellwish_p2 = 0;
        this.sellwish_p3 = 0;

        this.forecast_p1_one = 0;
        this.forecast_p1_two = 0;
        this.forecast_p1_three = 0;

        this.forecast_p2_one = 0;
        this.forecast_p2_two = 0;
        this.forecast_p2_three = 0;

        this.forecast_p3_one = 0;
        this.forecast_p3_two = 0;
        this.forecast_p3_three = 0;

        this.selldirect_quantity_p1 = 0;
        this.selldirect_quantity_p2 = 0;
        this.selldirect_quantity_p3 = 0;

        this.selldirect_penalty_p1 = "0.0";
        this.selldirect_penalty_p2 = "0.0";
        this.selldirect_penalty_p3 = "0.0";

        this.selldirect_price_p1 = "0.0";
        this.selldirect_price_p2 = "0.0";
        this.selldirect_price_p3 = "0.0";
    }

    // go to the next step in the formular
    next() {
        // validate period  
        if (this.page === 0 && (!this.planningService.currentPeriod || this.planningService.currentPeriod < 0)) {
            this.toastr.error(this.translationService.instant('planning_overview.toastr.wrong_period'));
            return;
        }
        // check if xml document has been loaded
        if (this.page === 0 && (<HTMLInputElement>document.getElementById("resultsXMLUpload")).value === "") {
            this.toastr.error(this.translationService.instant('planning_overview.toastr.missing_xml_document'));
            return;
        }
        // check if xml is valid (period and type of document)
        if (this.page === 0 && (this.planningService.errorWithXML || (this.planningService.currentPeriod - 1 !== this.planningService.xmlPeriod))) {
            this.toastr.error(this.translationService.instant('planning_overview.toastr.xml_error'));
            return;
        }
        // validate sellwish
        if (this.page === 1 && (
            this.sellwish_p1 < 0 ||
            this.sellwish_p2 < 0 ||
            this.sellwish_p3 < 0)) {
            this.toastr.error(this.translationService.instant('planning_overview.toastr.wrong_sellwish'));
            return;
        }
        if (this.page === 1 && (
            this.sellwish_p1 > 1000 ||
            this.sellwish_p2 > 1000 ||
            this.sellwish_p3 > 1000)) {
            this.toastr.warning(this.translationService.instant('planning_overview.toastr.very_high_sellwish'));
        }

        if (this.page !== 3) {
            this.page++;
        } else {
            this.planningService.startPlanning(this.createInputJSON())
                .then((result) => {
                    // console.log('ok');
                    this.planningService.isLoading = false;
                    this.planningService.startedPlanning = true;
                    this.router.navigate(['/app/planning/production']);
                }).catch((err: Response) => {
                    this.toastr.error(err.text());
                    console.error(err);
                    this.planningService.isLoading = false;
                    this.planningService.startedPlanning = false;
                    this.page = 0;
                });
        }

        // store input data in local storage
        localStorage.setItem("ibsys2InputLastPeriod", JSON.stringify(
            {
                ...this.createInputJSON(),
                enabledSellDirect: this.enabledSellDirect,
            }
        ));
    }

    // go to the last page of the formular
    back() {
        this.page--;
    }

    // disabled selldirects
    disableSellDirect() {
        this.enabledSellDirect = false;
        this.next();
    }

    /** 
     * create the JSON for the input data      
     */
    createInputJSON() {
        return {
            results: {
                game: 1,
                group: 6,
                period: this.planningService.currentPeriod,
            },
            input: {
                sellwish: {
                    items: [
                        {
                            "article": "1",
                            "quantity": this.sellwish_p1 || 0
                        },
                        {
                            "article": "2",
                            "quantity": this.sellwish_p2 || 0
                        },
                        {
                            "article": "3",
                            "quantity": this.sellwish_p3 || 0
                        }
                    ]
                },
                forecasts: {
                    forecast_one: {
                        items: [
                            {
                                "article": "1",
                                "quantity": this.forecast_p1_one || 0
                            },
                            {
                                "article": "2",
                                "quantity": this.forecast_p2_one || 0
                            },
                            {
                                "article": "3",
                                "quantity": this.forecast_p3_one || 0
                            }
                        ]
                    },
                    forecast_two: {
                        items: [
                            {
                                "article": "1",
                                "quantity": this.forecast_p1_two || 0
                            },
                            {
                                "article": "2",
                                "quantity": this.forecast_p2_two || 0
                            },
                            {
                                "article": "3",
                                "quantity": this.forecast_p3_two || 0
                            }
                        ]
                    },
                    forecast_three: {
                        items: [
                            {
                                "article": "1",
                                "quantity": this.forecast_p1_three || 0
                            },
                            {
                                "article": "2",
                                "quantity": this.forecast_p2_three || 0
                            },
                            {
                                "article": "2",
                                "quantity": this.forecast_p3_three || 0
                            }
                        ]
                    },
                },
                "selldirect": {
                    "items": [
                        {
                            "article": "1",
                            "quantity": this.enabledSellDirect ? (this.selldirect_quantity_p1 || 0) : 0,
                            "price": this.enabledSellDirect ? (this.selldirect_price_p1 || "0.0") : "0.0",
                            "penalty": this.enabledSellDirect ? (this.selldirect_penalty_p1 || "0.0") : "0.0",
                        },
                        {
                            "article": "2",
                            "quantity": this.enabledSellDirect ? (this.selldirect_quantity_p2 || 0) : 0,
                            "price": this.enabledSellDirect ? (this.selldirect_price_p2 || "0.0") : "0.0",
                            "penalty": this.enabledSellDirect ? (this.selldirect_penalty_p2 || "0.0") : "0.0",
                        },
                        {
                            "article": "3",
                            "quantity": this.enabledSellDirect ? (this.selldirect_quantity_p3 || 0) : 0,
                            "price": this.enabledSellDirect ? (this.selldirect_price_p3 || "0.0") : "0.0",
                            "penalty": this.enabledSellDirect ? (this.selldirect_penalty_p3 || "0.0") : "0.0",
                        }
                    ]
                },
                "safetystock": {
                    "items": this.safetyStock,
                }
            }
        }
    }

    cancelPlanningPreparation() {
        this.modal.openModal(this.translationService.instant('planning_overview.modals.cancelPreparation.text'), this.translationService.instant('planning_overview.modals.cancelPreparation.title'), false, () => {
            this.planningService.isLoading = false;
            this.router.navigate(['/app']);
        });
    }

    // if the slider changes
    onChangeDefaultSafetyStock(e) {
        this.safetyStock.map(part => {
            part.quantity = e;
        });
    }

    openHelp(key: string) {
        this.modal.openModal(this.translationService.instant(key), this.translationService.instant('modal.title.help'));
    }

    cancelLoading() {
        this.modal.openModal(this.translationService.instant('planning_overview.modals.cancelCalculation.text'), this.translationService.instant('planning_overview.modals.cancelCalculation.title'), false, () => {
            this.planningService.isLoading = false;
            this.router.navigate(['/app/planning']);
        });
    }
}
