import { Injectable, Inject } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class StatisticService {

    private url: string;

    constructor(private http: Http, @Inject('ApiEndpoint') private apiEndpoint) {
        this.url = apiEndpoint + '/api/rest/statitistic/';        
    }

    // GET Request
    /*
    geChart1() {
        return this.http.get(this.url, this.getHeaders())
            .toPromise()
            .then(response => response.json() as Article[])
            .catch(this.handleError);
    }
    */
}