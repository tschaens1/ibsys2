import { LoginService } from './../login/login.service';
import { Component } from '@angular/core';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashBoardComponent {
    optionsChart1: HighchartsOptions;
    optionsChart2: HighchartsOptions;
    optionsChart3: HighchartsOptions;
    optionsChart4: HighchartsOptions;

    constructor() {
        this.optionsChart1 = {
            title: { text: 'simple chart' },
            series: [{
                data: [29.9, 71.5, 106.4, 129.2],
            }],
            credits: false
        };
        this.optionsChart2 = {
            chart: { type: 'bar' },
            title: { text: 'dynamic data example' },
            series: [{ data: [2, 3, 5, 8, 13] }],
            credits: false
        };
        this.optionsChart3 = {
            chart: { type: 'bar' },
            title: { text: 'dynamic data example' },
            series: [{
                name: 'John',
                data: [5, 3, 4, 7, 2]
            }, {
                name: 'Jane',
                data: [2, 2, 3, 2, 1]
            }, {
                name: 'Joe',
                data: [3, 4, 4, 2, 5]
            }],
            credits: false
        };
    }

    public doughnutChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail-Order Sales'];
    public doughnutChartData: number[] = [350, 450, 100];
    public doughnutChartType: string = 'doughnut';

    // events
    public chartClicked(e: any): void {
        console.log(e);
    }

    public chartHovered(e: any): void {
        console.log(e);
    }
}