import { Injectable } from '@angular/core';
import { Component, OnInit, ElementRef, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';

@Injectable()
export class PlanningService implements OnInit {
    // for ui states
    startedPlanning: boolean;
    isLoading: boolean;

    // urls for upload
    xmlUploadUrl: string;
    inputUploadUrl: string;

    // documents that are uploaded to server
    inputJSON: any;
    xmlDocument: any;

    constructor(
        private http: Http,
        @Inject('ApiEndpoint') private apiEndpoint
    ) { }

    ngOnInit() {
        this.startedPlanning = false;
        this.isLoading = false;

        // create the upload url
        this.xmlUploadUrl = this.apiEndpoint + '/api/rest/file/xml';

        // create the upload url
        this.inputUploadUrl = this.apiEndpoint + '/api/rest/inputs';
    }

    /**
     * start planning of period
     * 
     * @param {any} json JSON-Data of the input forms. 
     * These inputs are required to calculate on the server side.          
     */
    startPlanning(json: any): Promise<any> {
        this.inputJSON = json;
        this.startedPlanning = true;
        if (this.xmlDocument === undefined) {
            throw new Error('XML is undefined!');
        }
        if (this.inputJSON === undefined) {
            throw new Error('Input data is not defined!');
        }        

        // send data to the server
        return new Promise((resolve, reject) => {
            this.sendXMLToServer().then(() => resolve()).catch((err) => reject(err));
            this.sendInputsToServer();
        })
    }

    /**
     * Send the input data to server. The input data is required
     * for the calculations on the server.
     */
    private sendInputsToServer() {
        this.isLoading = true;

        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify(this.inputJSON);
        return this.http.post(this.xmlUploadUrl, body, { headers: headers })
            .toPromise()
            .then(() => console.log('Input data successfully uploaded to server as JSON'))
            .catch(err => console.error('Could not upload input data as JSON to server', err));
    }

    /**
     * Send the xml to the server.          
     */
    private sendXMLToServer() {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({ content: encodeURIComponent(this.xmlDocument) });
        return this.http.post(this.xmlUploadUrl, body, { headers: headers })
            .toPromise()
            .then(() => console.log('XML successfully uploaded to server'))
            .catch(err => console.error('Could not upload XML to server', err));
    }

}