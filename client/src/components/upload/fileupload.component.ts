import { Component, ElementRef, Inject } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { ToastsManager } from 'ng2-toastr/ng2-toastr';
import { PlanningService } from './../planning/planning.service';

@Component({
    selector: 'file-upload',
    template: '<input type="file"  accept=".xml">'
})
export class FileUploadComponent {
    uploadUrl: string;
    xmlObject: string;

    constructor(
        private http: Http,
        private el: ElementRef,
        @Inject('ApiEndpoint') private apiEndpoint,
        private toastr: ToastsManager,
        private planningService: PlanningService,
    ) {
        this.uploadUrl = apiEndpoint + '/api/rest/file/xml';
    }

    readXML() {
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { // a file was selected
            let file: File = inputEl.files[0];
            var myReader: FileReader = new FileReader();

            if (file.type !== 'text/xml') {
                this.toastr.error('This is not an XML!')
                return;
            }

            myReader.onloadend = (e) => {
                this.xmlObject = myReader.result;
                this.planningService.xmlDocument = myReader.result;
                this.planningService.sendXMLToServer();
            }
            myReader.readAsText(file);
        }
    }
}