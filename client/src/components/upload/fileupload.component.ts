import { TranslationService } from '../translate/translate.service';
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
        private translationService: TranslationService,
    ) {
        this.uploadUrl = apiEndpoint + '/api/rest/file/xml';
    }

    readXML() {
        let inputEl = this.el.nativeElement.firstElementChild;
        if (inputEl.files.length > 0) { // a file was selected
            let file: File = inputEl.files[0];
            var xmlFileReader: FileReader = new FileReader();

            if (file.type !== 'text/xml') {
                this.toastr.error(this.translationService.instant('planning_overview.toastr.wrong_document_type'));
                this.planningService.errorWithXML = true;
                return;
            }

            xmlFileReader.onloadend = (e) => {
                this.xmlObject = xmlFileReader.result;
                this.planningService.xmlDocument = xmlFileReader.result;
                this.planningService.sendXMLToServer()
                    .catch(err => {
                        this.toastr.error(err);
                    });
            }
            xmlFileReader.readAsText(file);
        }
    }
}