import { AfterViewInit, Component, OnInit } from '@angular/core';
import { TranslationService } from './../translate/translate.service';

@Component({
    selector: 'help',
    templateUrl: './help.component.html',
    styleUrls: ['./help.component.scss']
})
export class HelpComponent implements AfterViewInit {
    private currentLanguage: string;

    constructor() { }

    ngAfterViewInit(): void {
        $('.collapsible').collapsible();
    }
}