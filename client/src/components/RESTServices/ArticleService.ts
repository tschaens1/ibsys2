import { Injectable, Inject } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

interface Article {
    id: number;
    amount: number;
    startamount: number;
    price: number;
    percentage: number;
}

@Injectable()
export class ArticleService {

    private uploadUrl: string;

    constructor(private http: Http, @Inject('ApiEndpoint') private apiEndpoint) {
        this.uploadUrl = apiEndpoint + '/api/rest/articles';        
    }

    // GET Request
    getArticles() {
        return this.http.get(this.uploadUrl, this.getHeaders())
            .toPromise()
            .then(response => response.json() as Article[])
            .catch(this.handleError);
    }

    // GET Article by Id
    getArticleById(id: number) {
        return this.http.get(this.uploadUrl + '/' + id, this.getHeaders())
            .toPromise()
            .then(response => response.json() as Article)
            .catch(this.handleError);
    }

    // POST
    addArticle(article: any): Promise<Article> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({ id: article.id, amount: article.amount, startamount: article.startamount, percentage: article.percentage, price: article.price });
        return this.http.post(this.uploadUrl, body, { headers: headers })
            .toPromise()
            .then(this.extractData)
            .catch(this.handleError);
    }

    // DELETE 
    deleteArticle(article: Article) {
        let url = `${this.uploadUrl}/${article.id}`;
        return this.http
            .delete(url, this.getHeaders())
            .toPromise()
            .catch(this.handleError);
    }

    // Update
    updateArticle(article: any) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({ id: article.id, amount: article.amount, startamount: article.startamount, percentage: article.percentage, price: article.price });
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