import { Injectable, Inject, EventEmitter } from '@angular/core';
import { Settings } from './settings';
import { TranslationService } from './../translate/translate.service';

@Injectable()
export class SettingsService {
    settings: Settings;

    constructor(private translationService: TranslationService) {
        this.settings = JSON.parse(localStorage.getItem('ibsys2Settings')) || new Settings('en');
    }

    // login
    setLoginBehaviour(stayLoggedIn: boolean) {
        this.settings.stayLoggedIn = stayLoggedIn;
        this.updateLocalStorage();
    }
    getLoginBehaviour = () => this.settings.stayLoggedIn;

    // language settings
    setLanguage(language: string) {
        this.settings.language = language;
        this.updateLocalStorage();
    }
    getLanguage = () => this.settings.language;

    private updateLocalStorage() {
        localStorage.setItem('ibsys2Settings', JSON.stringify(this.settings));
    }
}