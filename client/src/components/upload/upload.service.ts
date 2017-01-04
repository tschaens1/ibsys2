import { Injectable, Inject } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

interface Sample { }

@Injectable()
export class UploadService {

    private uploadUrl: string;

    constructor(private http: Http, @Inject('ApiEndpoint') private apiEndpoint) {
        this.uploadUrl = apiEndpoint + '/api/rest/uploadXML';
    }

    // GET Request
    getSamples() {
        return this.http.get(this.uploadUrl, this.getHeaders())
            .toPromise()
            .then(response => response.json() as Sample[])
            .catch(this.handleError);
    }

    // GET Sample by Id
    getSampleById(id: number) {
        return this.http.get(this.uploadUrl + '/' + id, this.getHeaders())
            .toPromise()
            .then(response => response.json() as Sample)
            .catch(this.handleError);
    }

    // POST
    addSample(sample: any): Promise<any> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({
            id: null,
            sample,
        });
        return this.http.post(this.uploadUrl, body, { headers: headers })
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    // DELETE 
    deleteSample(sample: any) {
        let url = `${this.uploadUrl}/${sample.id}`;
        return this.http
            .delete(url, this.getHeaders())
            .toPromise()
            .catch(this.handleError);
    }

    // Update
    updateSample(sample: any) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({
            id: sample.id,
            sample
        });
        return this.http
            .put(this.uploadUrl, body, { headers: headers })
            .toPromise()
            // .then(() => console.log('success!'))
            .catch(this.handleError);
    }

    // Returns the options for the headers.
    private getHeaders() {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers, body: '' });
        return options;
    }

    // Extract the data of the response.
    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    // Error handling
    private handleError(error: any) {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}