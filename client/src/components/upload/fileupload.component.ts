import { Component, ElementRef, Inject } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Component({
    selector: 'file-upload',
    template: '<input type="file">'
})
export class FileUploadComponent {
    uploadUrl: string;
    xmlObject: string;

    constructor(private http: Http, private el: ElementRef, @Inject('ApiEndpoint') private apiEndpoint) {
        this.uploadUrl = apiEndpoint + '/api/rest/file/xml';
    }

    readXML() {
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { // a file was selected
            let file: File = inputEl.files[0];
            var myReader: FileReader = new FileReader();

            myReader.onloadend = (e) => {
                this.xmlObject = myReader.result;
                this.sendToServer(myReader.result);
            }
            myReader.readAsText(file);
        }
    }

    sendToServer(content) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let body = JSON.stringify({ content: encodeURIComponent(content) });
        return this.http.post(this.uploadUrl, body, { headers: headers })
            .toPromise()
            .then(() => console.log('ok'))
            .catch(err => console.error('Bad request', err));
    }
}