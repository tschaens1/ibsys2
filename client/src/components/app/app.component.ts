import { LoginService } from './../login/login.service';
import { Component, OnInit } from '@angular/core';
import { TranslationService } from './../translate/translate.service';
import { SettingsService } from './../settings/settings.service';

require('./../../styles/styles.global.scss');
// require('./../../images/favicon.ico');

@Component({
    selector: 'app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
    public translatedText: string;
    public supportedLanguages: any[];
    constructor(private translationService: TranslationService, private settingsService: SettingsService) { }

    ngOnInit() {
        // store all supported languages
        this.supportedLanguages = [
            { display: 'English', value: 'en' },
            { display: 'German', value: 'de' },
        ];

        this.selectLanguage(this.settingsService.getLanguage() ? this.settingsService.getLanguage() : navigator.language);
        this.translationService.setDefaultLanguage('en');
        this.translationService.enableFallback(true);
    }    

    isCurrentLanguage(language: string) {
        return language === this.translationService.currentLanguage;
    }

    selectLanguage(language: string) {
        this.translationService.use(language);
        this.refreshText();
    }

    refreshText() {
        this.translatedText = this.translationService.instant('hello world');
    }
}
