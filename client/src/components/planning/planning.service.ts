import { TranslationService } from '../translate/translate.service';
import { BehaviorSubject } from 'rxjs/Rx';
import { Injectable } from '@angular/core';
import { Component, OnInit, ElementRef, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Headers, Http, Response } from '@angular/http';
var js2xmlparser = require('js2xmlparser');
var FileSaver = require('file-saver');

@Injectable()
export class PlanningService implements OnInit {
    // for ui states
    startedPlanning: boolean;
    isLoading: boolean;
    errorWithXML: boolean = false;

    currentPeriod: number;
    xmlPeriod: number;

    // urls for upload
    xmlUploadUrl: string;
    inputUploadUrl: string;

    // documents that are uploaded to server
    inputJSON: any;
    xmlDocument: any;

    // document that is needed to create the input xml for the server
    inputDataForSimulatorAsJSON: any = {
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
                        "quantity": 0,
                    }
                },
                {
                    "@": {
                        "article": 2,
                        "quantity": 0,
                    }
                },
                {
                    "@": {
                        "article": 3,
                        "quantity": 0,
                    }
                }
            ]
        },
        "selldirect": {
            "item": [
                {
                    "@": {
                        "article": 1,
                        "quantity": 0,
                        "price": "0.0",
                        "penalty": "0.0",
                    }
                },
                {
                    "@": {
                        "article": 2,
                        "quantity": 0,
                        "price": "0.0",
                        "penalty": "0.0",
                    }
                },
                {
                    "@": {
                        "article": 3,
                        "quantity": 0,
                        "price": "0.0",
                        "penalty": "0.0",
                    }
                }
            ]
        },
        "orderlist": {
            "order": []
        },
        "productionlist": {
            "production": []
        },
        "workingtimelist": {
            "workingtime": []
        }
    }

    orderlist: number[] = [21, 22, 23, 24, 25, 27, 32, 34, 36, 37, 38, 39, 40, 41, 42, 44, 46, 47, 48, 52, 53, 57, 58, 59];
    productionlist: number[] = [4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 26, 49, 54, 29, 50, 55, 30, 51, 56, 31, 1, 2, 3];
    workingtimelist: number[] = [1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];

    constructor(
        private http: Http,
        @Inject('ApiEndpoint') private apiEndpoint,
        private translationService: TranslationService,
    ) {
        this.startedPlanning = false;
        this.isLoading = false;

        const storage = JSON.parse(localStorage.getItem("ibsys2InputLastPeriod"));

        if (storage && storage.results && storage.results.period) {
            this.currentPeriod = storage.results.period;
        } else {
            this.currentPeriod = undefined;
        }


        this.xmlUploadUrl = this.apiEndpoint + `/api/rest/files/result`;

        // load the orderlist, productionlist and workingtimelist into the inputDataForSimulatorAsJSON
        // orderlist
        this.inputDataForSimulatorAsJSON.orderlist.order = this.orderlist.map(i => {
            return {
                "@": {
                    "article": i,
                    "quantity": 0,
                    "modus": 5,
                }
            }
        });

        // productionlist
        this.inputDataForSimulatorAsJSON.productionlist.production = this.productionlist.map(i => {
            return {
                "@": {
                    "article": i,
                    "quantity": 0,
                }
            }
        });

        // workingtimelist
        this.inputDataForSimulatorAsJSON.workingtimelist.workingtime = this.workingtimelist.map(i => {
            return {
                "@": {
                    "station": i,
                    "shift": 1,
                    "overtime": 1,
                }
            }
        });
    }

    ngOnInit() {

    }

    /**
     * start planning of period
     * 
     * @param {any} json JSON-Data of the input forms. 
     * These inputs are required to calculate on the server side.          
     */
    startPlanning(json: any): Promise<any> {
        this.inputJSON = json;

        this.inputDataForSimulatorAsJSON.sellwish.item = json.input.sellwish.items.map(item => ({ '@': item }));
        this.inputDataForSimulatorAsJSON.selldirect.item = json.input.selldirect.items.map(item => ({ '@': item }));;

        this.isLoading = true;
        if (this.xmlDocument === undefined) {
            throw new Error('XML is undefined!');
        }
        if (this.inputJSON === undefined) {
            throw new Error('Input data is not defined!');
        }

        // create the upload url
        this.xmlUploadUrl = this.apiEndpoint + `/api/rest/files/result`;

        // create the upload url
        this.inputUploadUrl = this.apiEndpoint + `/api/rest/periods/${this.inputJSON.results.period}/planning`;

        // send data to the server                                
        return new Promise((resolve, reject) => {
            // send data to the server            
            this.sendInputsToServer().catch(err => { console.error(err); reject(err) }).then((response: Response) => {
                if (!response) {
                    reject('Response is undefined!');
                    return;
                }
                const result = response.json();
                console.log('Result:', result);
                this.inputDataForSimulatorAsJSON.productionlist.production = result.input.productionlist.map(production => {
                    return {
                        "@": production
                    }
                });

                this.inputDataForSimulatorAsJSON.orderlist.order = result.input.orderlist.map(order => {
                    return {
                        "@": order
                    }
                });

                this.inputDataForSimulatorAsJSON.workingtimelist.workingtime = result.input.workingtimelist.map(workingtime => {
                    return {
                        "@": workingtime
                    }
                });
                resolve();
            });
        });
    }
    /**
     * Send the input data to server. The input data is required
     * for the calculations on the server.
     */
    sendInputsToServer() {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify(this.inputJSON);
        return this.http.post(this.inputUploadUrl, body, { headers: headers }).toPromise();
    }

    /**
     * Send the xml to the server.          
     */
    sendXMLToServer() {
        let headers = new Headers({ 'Content-Type': 'application/json' });

        // parse the xml to check the period
        let xmlDoc = $.parseXML(this.xmlDocument),
            $xml = $(xmlDoc),
            title = $xml.find("results").attr('period');

        this.xmlPeriod = parseInt(title);

        // if the period of the xml is the last period
        if ((this.currentPeriod - 1) === this.xmlPeriod) {
            let body = JSON.stringify({ content: encodeURIComponent(this.xmlDocument) });
            return this.http.post(this.xmlUploadUrl, body, { headers: headers }).toPromise();
        } else {
            return Promise.reject(this.translationService.instant('planning_overview.toastr.xml_of_wrong_period'));
        }
    }

    /**
     * Generate the input XML for the SCSimulator. It is based
     * on the input data that must be typed into the input fields by the user.
     */
    generateInputXMLForSimulator() {
        // create the xml
        // slice the first 22 characters because they are like this: <?xml version='1.0'?>
        return new Promise((resolve, reject) => {
            try {
                let outputxmlJSON = JSON.parse(JSON.stringify(this.inputDataForSimulatorAsJSON));
                outputxmlJSON.orderlist.order.map(item => {
                    delete item['@'].toolate;
                });
                outputxmlJSON.workingtimelist.workingtime.map(item => {
                    delete item['@'].toomuchwork;
                });
                console.info(outputxmlJSON);
                const xml: string = js2xmlparser.parse("input", outputxmlJSON).slice(22);

                // download the XML file
                var blob = new Blob([xml], { type: "text/plain;charset=utf-8" });
                FileSaver.saveAs(blob, `inputXML_${Date.now()}.xml`);
            } catch (err) {
                reject(err);
            }
            resolve();
        });
    }

    updateInputJSON(newData) {
        this.inputDataForSimulatorAsJSON = { ...this.inputDataForSimulatorAsJSON, newData }
        console.log(this.inputDataForSimulatorAsJSON);
    }
}