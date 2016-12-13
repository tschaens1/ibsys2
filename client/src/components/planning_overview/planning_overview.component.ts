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

    // data
    period: number;
    sellwish_p1: number;
    sellwish_p2: number;
    sellwish_p3: number;

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
    }

    // go to the next step in the formular
    next() {
        // validate period  
        if (this.page === 0 && (!this.period || this.period < 0)) {
            this.toastr.error('Wrong period!');
            return;
        }
        // validate xml upload
        if ((<HTMLInputElement>document.getElementById("resultsXMLUpload")).value === "") {
            this.toastr.error('Missing xml document!');
            return;
        }
        // validate sellwish
        if (this.page === 1 && (!this.sellwish_p1 || this.sellwish_p1 < 0 ||
            !this.sellwish_p2 || this.sellwish_p2 < 0 ||
            !this.sellwish_p3 || this.sellwish_p3 < 0)) {
            this.toastr.error('Wrong sellwish!');
            return;
        }

        // validate produce
        if (this.page === 3 && (!this.produce_p1 || this.produce_p1 < 0 ||
            !this.produce_p2 || this.produce_p2 < 0 ||
            !this.produce_p3 || this.produce_p3 < 0)) {
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
                            "quantity": this.sellwish_p1
                        },
                        {
                            "-article": "2",
                            "-quantity": this.sellwish_p2
                        },
                        {
                            "-article": "3",
                            "-quantity": this.sellwish_p3
                        }
                    ]
                },
                production: {
                    "items": [
                        {
                            "article": "1",
                            "quantity": this.produce_p1
                        },
                        {
                            "article": "2",
                            "quantity": this.produce_p2
                        },
                        {
                            "article": "3",
                            "quantity": this.produce_p3
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
