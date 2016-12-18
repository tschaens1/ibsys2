import { Injectable } from '@angular/core';
import { Component, OnInit, ElementRef, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http, Headers } from '@angular/http';
var js2xmlparser = require('js2xmlparser');
var FileSaver = require('file-saver');

@Injectable()
export class PlanningService implements OnInit {
    // for ui states
    startedPlanning: boolean = true;
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
        this.startedPlanning = true;
        this.isLoading = false;
    }

    /**
     * start planning of period
     * 
     * @param {any} json JSON-Data of the input forms. 
     * These inputs are required to calculate on the server side.          
     */
    startPlanning(json: any): Promise<any> {
        this.inputJSON = json;
        this.isLoading = true;
        if (this.xmlDocument === undefined) {
            throw new Error('XML is undefined!');
        }
        if (this.inputJSON === undefined) {
            throw new Error('Input data is not defined!');
        }

        // create the upload url
        this.xmlUploadUrl = this.apiEndpoint + `/api/rest/files`;

        // create the upload url
        this.inputUploadUrl = this.apiEndpoint + `/api/rest/games/${1}/groups/${6}/periods/${this.inputJSON.results.period}/plannings`;

        // send data to the server
        return this.sendXMLToServer()
            .then(() => {
                return this.sendInputsToServer();
            });
    }
    /**
     * Send the input data to server. The input data is required
     * for the calculations on the server.
     */
    private sendInputsToServer() {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify(this.inputJSON);
        return this.http.post(this.inputUploadUrl, body, { headers: headers }).toPromise()
    }

    /**
     * Send the xml to the server.          
     */
    private sendXMLToServer() {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({ content: encodeURIComponent(this.xmlDocument) });
        return this.http.post(this.xmlUploadUrl, body, { headers: headers }).toPromise()
    }

    /**
     * Generate the input XML for the SCSimulator. It is based
     * on the input data that must be typed into the input fields by the user.
     */
    generateInputXMLForSimulator() {
        let obj = {
            "qualitycontrol": {
                "@": {
                    "type": "no",
                    "losequantity": 0,
                    "delay": 0,
                },
            },
            "sellwish": {
                "item": [
                    {
                        "@": {
                            "article": 1,
                            "quantity": 100,
                        }
                    },
                    {
                        "@": {
                            "article": 2,
                            "quantity": 100,
                        }
                    },
                    {
                        "@": {
                            "article": 3,
                            "quantity": 100,
                        }
                    }
                ]
            },
        };

        // create the xml
        // slice the first 22 characters because they are like this: <?xml version='1.0'?>
        const xml: string = js2xmlparser.parse("input", obj).slice(22);

        // download the XML file
        var blob = new Blob([xml], { type: "text/plain;charset=utf-8" });
        FileSaver.saveAs(blob, `inputXML${Date.now()}.xml`);
    }
}