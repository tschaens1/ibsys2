import { Component, OnInit, ElementRef, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';
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

    // data
    period: number;
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

    selldirect_price_p1: number;
    selldirect_price_p2: number;
    selldirect_price_p3: number;

    selldirect_quantity_p1: number;
    selldirect_quantity_p2: number;
    selldirect_quantity_p3: number;

    selldirect_penalty_p1: number;
    selldirect_penalty_p2: number;
    selldirect_penalty_p3: number;

    produce_p1: number;
    produce_p2: number;
    produce_p3: number;


    defaultSafetystock: number = 50;
    enabledPartsSafetyStock: boolean = false;
    safetyStock = [];
    productionParts = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 26, 29, 30, 31, 49, 50, 51, 54, 55, 56];

    constructor(
        private router: Router,
        private planningService: PlanningService,
        private toastr: ToastsManager,
        private route: ActivatedRoute,
        private http: Http,
        private el: ElementRef,
        @Inject('ApiEndpoint') private apiEndpoint
    ) { }

    ngOnInit() {
        // create safety stock objects
        this.productionParts.map(part => {
            this.safetyStock.push({
                article: part,
                quantity: this.defaultSafetystock
            })
        });

        this.loadInputsOfLastPeriod();
    }

    // load input of last period
    loadInputsOfLastPeriod() {

        // load data from local storage
        this.inputOflastPeriod = JSON.parse(localStorage.getItem("ibsys2InputLastPeriod"));

        if (!this.inputOflastPeriod) {
            this.toastr.error('No data available');
        }

        try {
            this.period = this.inputOflastPeriod.results.period;

            this.sellwish_p1 = this.inputOflastPeriod.input.sellwish.items[0].quantity || 0;
            this.sellwish_p2 = this.inputOflastPeriod.input.sellwish.items[1].quantity || 0;
            this.sellwish_p3 = this.inputOflastPeriod.input.sellwish.items[2].quantity || 0;

            this.forecast_p1_one = this.inputOflastPeriod.input.forcasts.forecast_one.items[0].quantity || 0;
            this.forecast_p1_two = this.inputOflastPeriod.input.forcasts.forecast_one.items[0].quantity || 0;
            this.forecast_p1_three = this.inputOflastPeriod.input.forcasts.forecast_one.items[0].quantity || 0;

            this.forecast_p2_one = this.inputOflastPeriod.input.forcasts.forecast_one.items[1].quantity || 0;
            this.forecast_p2_two = this.inputOflastPeriod.input.forcasts.forecast_one.items[1].quantity || 0;
            this.forecast_p2_three = this.inputOflastPeriod.input.forcasts.forecast_one.items[1].quantity || 0;

            this.forecast_p3_one = this.inputOflastPeriod.input.forcasts.forecast_one.items[2].quantity || 0;
            this.forecast_p3_two = this.inputOflastPeriod.input.forcasts.forecast_one.items[2].quantity || 0;
            this.forecast_p3_three = this.inputOflastPeriod.input.forcasts.forecast_one.items[2].quantity || 0;

            this.enabledSellDirect = this.inputOflastPeriod.enabledSellDirect || false;

            this.selldirect_price_p1 = this.inputOflastPeriod.input.selldirect.items[0].price || 0;
            this.selldirect_price_p2 = this.inputOflastPeriod.input.selldirect.items[1].price || 0;
            this.selldirect_price_p3 = this.inputOflastPeriod.input.selldirect.items[2].price || 0;

            this.selldirect_quantity_p1 = this.inputOflastPeriod.input.selldirect.items[0].quantity || 0;
            this.selldirect_quantity_p2 = this.inputOflastPeriod.input.selldirect.items[1].quantity || 0;
            this.selldirect_quantity_p3 = this.inputOflastPeriod.input.selldirect.items[2].quantity || 0;

            this.selldirect_penalty_p1 = this.inputOflastPeriod.input.selldirect.items[0].penalty || 0;
            this.selldirect_penalty_p2 = this.inputOflastPeriod.input.selldirect.items[1].penalty || 0;
            this.selldirect_penalty_p3 = this.inputOflastPeriod.input.selldirect.items[2].penalty || 0;

            this.produce_p1 = this.inputOflastPeriod.input.production.items[0].quantity || 0;
            this.produce_p2 = this.inputOflastPeriod.input.production.items[1].quantity || 0;
            this.produce_p3 = this.inputOflastPeriod.input.production.items[2].quantity || 0;


            this.defaultSafetystock = this.inputOflastPeriod.defaultSafetystock;
        }
        catch (err) {
            console.log('Could not load inputs of last period!', err);
            this.clearInputs();
        }
    }

    clearInputs() {
        this.period = 0;

        this.sellwish_p1 = 0;
        this.sellwish_p2 = 0;
        this.sellwish_p3 = 0;

        this.selldirect_price_p1 = 0;
        this.selldirect_price_p2 = 0;
        this.selldirect_price_p3 = 0;

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

        this.selldirect_penalty_p1 = 0;
        this.selldirect_penalty_p2 = 0;
        this.selldirect_penalty_p3 = 0;

        this.produce_p1 = 0;
        this.produce_p2 = 0;
        this.produce_p3 = 0;


        this.defaultSafetystock = 50;
    }

    // go to the next step in the formular
    next() {
        // validate period  
        if (this.page === 0 && (!this.period || this.period < 0)) {
            this.toastr.error('Wrong period!');
            return;
        }
        // validate xml upload
        if (this.page === 0 && (<HTMLInputElement>document.getElementById("resultsXMLUpload")).value === "") {
            this.toastr.error('Missing xml document!');
            return;
        }
        // validate sellwish
        if (this.page === 1 && (
            this.sellwish_p1 < 0 ||
            this.sellwish_p2 < 0 ||
            this.sellwish_p3 < 0)) {
            this.toastr.error('Wrong sellwish!');
            return;
        }
        if (this.page === 1 && (
            this.sellwish_p1 > 1000 ||
            this.sellwish_p2 > 1000 ||
            this.sellwish_p3 > 1000)) {
            this.toastr.warning('Very high sellwish!');
        }

        // validate produce
        if (this.page === 3 && (this.produce_p1 < 0 ||
            this.produce_p2 < 0 ||
            this.produce_p3 < 0)) {
            this.toastr.error('Wrong production values!');
            return;
        }

        if (this.page !== 4) {
            this.page++;
        } else {
            this.planningService.startPlanning(this.createInputJSON()).then((result) => {
                alert('works');
            });
        }

        // store input data in local storage
        localStorage.setItem("ibsys2InputLastPeriod", JSON.stringify(
            {
                ...this.createInputJSON(),
                defaultSafetystock: this.defaultSafetystock,
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
                game: 999,
                group: 6,
                period: this.period
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
                forcasts: {
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
                production: {
                    "items": [
                        {
                            "article": "1",
                            "quantity": this.produce_p1 || 0
                        },
                        {
                            "article": "2",
                            "quantity": this.produce_p2 || 0
                        },
                        {
                            "article": "3",
                            "quantity": this.produce_p3 || 0
                        }
                    ]
                },
                "selldirect": {
                    "items": [
                        {
                            "article": "1",
                            "quantity": this.selldirect_quantity_p1 || 0,
                            "price": this.selldirect_price_p1 || 0,
                            "penalty": this.selldirect_penalty_p1 || 0,
                        },
                        {
                            "article": "2",
                            "quantity": this.selldirect_quantity_p2 || 0,
                            "price": this.selldirect_price_p2 || 0,
                            "penalty": this.selldirect_penalty_p2 || 0,
                        },
                        {
                            "article": "3",
                            "quantity": this.selldirect_quantity_p3 || 0,
                            "price": this.selldirect_price_p3 || 0,
                            "penalty": this.selldirect_penalty_p3 || 0,
                        }
                    ]
                },
                "safetystock": {
                    "items": this.safetyStock
                }
            }
        }
    }

    // if the slider changes
    onChangeDefaultSafetyStock(e) {
        this.safetyStock.map(part => {
            part.quantity = e;
        })
    }
}
