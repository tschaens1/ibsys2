import { BehaviorSubject } from 'rxjs/Rx';
import { Injectable } from '@angular/core';
import { Component, OnInit, ElementRef, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Headers, Http, RequestOptions, Response } from '@angular/http';

@Injectable()
export class DashboardService implements OnInit {
    uploadUrl: string;

    constructor(private http: Http, @Inject('ApiEndpoint') private apiEndpoint) {
        this.uploadUrl = apiEndpoint + '/api/rest/files/periods/1/result';
    }

    getResults(period: number) {
        this.uploadUrl = this.apiEndpoint + `/api/rest/files/periods/${period}/result`;
        return this.http.get(this.uploadUrl, this.getHeaders())
            .toPromise()
            .then(response => response.json())
    }

    getStock(period: number) {
        this.uploadUrl = this.apiEndpoint + `/api/rest/files/periods/${period}/stock`;
        return this.http.get(this.uploadUrl, this.getHeaders())
            .toPromise()
            .then(response => response.json())
    }

    ngOnInit(): void {

    }

    // Returns the options for the headers.
    private getHeaders() {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers, body: '' });
        return options;
    }
}