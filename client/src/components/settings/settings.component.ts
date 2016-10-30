import { Component, OnInit } from '@angular/core';
import { TranslationService } from './../translate/translate.service';
import { SettingsService } from './settings.service';

@Component({
    selector: 'settings',
    templateUrl: './settings.component.html',
    styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit {
    private currentLanguage: string;

    constructor(private translationService: TranslationService, private settingsService: SettingsService) { }

    changeLanguage(language: string) {
        this.translationService.use(language);
        this.settingsService.setLanguage(language);
    }

    ngOnInit() {
        this.translationService.onLanguageChanged.subscribe(language => console.info('language changed to ', language));
    }
}