import { ToastsManager } from 'ng2-toastr';
import { DashboardService } from './dashboard.service';
import { LoginService } from './../login/login.service';
import { Component, OnInit } from '@angular/core';
import { TranslationService } from '../translate/translate.service';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashBoardComponent implements OnInit {
    //optionsChart1: HighchartsOptions;
    optionsChart2: HighchartsOptions;
    optionsChart3: HighchartsOptions;
    optionsChart4: HighchartsOptions;

    //chart1: HighchartsChart;

    period: number = 1;
    results: any;
    stock: any;

    relevantStocksId = [];
    relevantStocksPct = [];

    productionParts = [4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 26, 29, 30, 31, 49, 50, 51, 54, 55, 56];
    productionEndParts = [1, 2, 3];
    purchaseparts = [21, 22, 23, 24, 25, 27, 28, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 52, 53, 57, 58, 59];
    valueProductionParts: number = 0;
    valueProductionEndParts: number = 0;
    valuePurchaseparts: number = 0;

    ngOnInit() {

    }

    loadCharts() {
        let self = this;

        self.relevantStocksId = [];
        self.relevantStocksPct = [];

        //Set the values for the warehouse stock
        for (let i in self.stock.article) {
            let id = self.stock.article[i].id;
            let pct = parseFloat(self.stock.article[i].pct);
            self.relevantStocksId.push(id);
            self.relevantStocksPct.push(pct);
        }

        //Set the values for the donut chart
        for (let i in self.stock.article) {
            let id = self.stock.article[i].id;
            let value = parseInt(self.stock.article[i].stockvalue);
            console.log(value);
            if (self.productionParts.includes(id)) {
                self.valueProductionParts += value;
            } else if (self.productionEndParts.includes(id)) {
                self.valueProductionEndParts += value;
            } else {
                self.valuePurchaseparts += value;
            }
        }

        this.optionsChart2 = {
            chart: {type: 'column' },
            title: { text: this.translationService.currentLanguage === 'de' ? "Lagerauslastung in %" : "Warehouse stock utilization in %", },

            plotOptions: {
                column: {
                    zones: [{
                        value: 40,
                        color: 'red'
                    }, {
                        value: 139,
                        color: 'grey'
                    }, {

                        color: 'orange'
                    }]
                },
            },
            yAxis: {
                title: {
                    text: '%'
                }
            },
            xAxis: {
                categories: self.relevantStocksId,
                title: {
                    text: 'ID´s'
                }
            },
            series: [{
                name: this.translationService.currentLanguage === 'de' ? "Lager" : "Stock warehouse",
                data: self.relevantStocksPct,
            }],
            credits: false,
        };
       
        this.optionsChart3 = {
            chart: { type: 'pie' },
            title: { text: this.translationService.currentLanguage === 'de' ? "Zusammensetzung Lager" : "Composition warehouse", },
            plotOptions: {
                pie: {
                    dataLabels: {
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    }
                }
            },
            series: [{
                data: [{
                    name: this.translationService.currentLanguage === 'de' ? "Eigenfertigung" : "Own products",
                    y: self.valueProductionParts
                }, {
                    name: this.translationService.currentLanguage === 'de' ? "Endprodukte" : "Final products",
                    y: self.valueProductionEndParts,
                }, {
                    name: this.translationService.currentLanguage === 'de' ? "Kaufteile" : "Purchasing parts",
                    y: self.valuePurchaseparts
                },]
            }],
            credits: false
        };
    }
    
    constructor(
        private dashboardService: DashboardService,
        private translationService: TranslationService,
        private toastr: ToastsManager,
    ) { }



    public doughnutChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
    public doughnutChartData: number[] = [100, 200, 300];
    public doughnutChartType: string = 'doughnut';

    // events
    public chartClicked(e: any): void {
        console.log(e);
    }

    public chartHovered(e: any): void {
        console.log(e);
    }

    getResults() {
        this.dashboardService.getResults(this.period).then(results => {
            console.log(results);
            this.results = results;
            this.getStock();
            //this.bla();
        }).catch((err) => {
            console.error(err);
            this.toastr.error(`Period ${this.period} not available`);
        });

    }

    getStock() {
        this.dashboardService.getStock(this.period).then(stock => {
            console.log(stock);
            this.stock = stock;
            this.loadCharts();
        }).catch((err) => {
            console.error(err);
            this.toastr.error(`Period ${this.period} not available`);
        });
    }
}