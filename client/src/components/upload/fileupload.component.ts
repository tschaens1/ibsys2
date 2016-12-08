import { Component, ElementRef, Inject } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Component({
    selector: 'file-upload',
    template: '<input type="file">'
})
export class FileUploadComponent {

    uploadUrl: string;
    xmlObject = {};

    constructor(private http: Http, private el: ElementRef, @Inject('ApiEndpoint') private apiEndpoint) {
        this.uploadUrl = apiEndpoint + '/api/rest/file/xml';
    }

    readXML() {
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { // a file was selected
            let file: File = inputEl.files[0];
            var myReader: FileReader = new FileReader();

            myReader.onloadend = (e) => {
                // console.log(myReader.result);
                this.xmlObject = { content: encodeURIComponent(myReader.result) };
                // console.log(this.xmlObject);
                console.info(encodeURIComponent(myReader.result).length);
                // console.log(myReader.result);
            }
            myReader.readAsText(file);

            let headers = new Headers({ 'Content-Type': 'application/json' });
            this.http
                .post(this.uploadUrl, this.xmlObject, { headers: headers })
                .toPromise()
                .then(() => console.log('ok'))
                .catch(() => console.error('Error in connection'));
        }
    }
}