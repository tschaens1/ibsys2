import { Component, ElementRef, Inject } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Component({
    selector: 'file-upload',
    template: '<input type="file">'
})
export class FileUploadComponent {

    uploadUrl: string;

    constructor(private http: Http, private el: ElementRef, @Inject('ApiEndpoint') private apiEndpoint) {
        this.uploadUrl = apiEndpoint + '/api/rest/uploadXML';
    }

    upload() {
        console.info('send data to server');
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { // a file was selected
            let file: FileList = inputEl.files[0];
            let headers = new Headers({ 'Content-Type': 'application/xml' });
            this.http
                .post(this.uploadUrl, file, { headers: headers })
                .toPromise()
                .then(() => alert('ok'))
                .catch(() => alert('not ok'));
        }
    }
}