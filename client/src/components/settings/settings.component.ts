import { Component, OnInit } from '@angular/core';
import { TranslationService } from './../translate/translate.service';

@Component({
    selector: 'settings',
    templateUrl: './settings.component.html',
    styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {
    private currentLanguage: string;

    constructor(private translationService: TranslationService) { }

    changeLanguage(language: string) {
        this.translationService.use(language);
    }

    ngOnInit() {
        this.translationService.onLanguageChanged.subscribe(language => console.info('language changed to ', language));
    }
}