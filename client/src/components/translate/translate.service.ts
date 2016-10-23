import { Injectable, Inject } from '@angular/core';
import { TRANSLATIONS } from './translations';
import * as _ from 'lodash';

@Injectable()
export class TranslationService {
    private _currentLang: string;

    public get currentLang() {
        return this._currentLang;
    }

    constructor( @Inject(TRANSLATIONS) private _translations: any) {
    }

    // set the current language
    public use(lang: string): void {
        this._currentLang = lang;        
    }

    private translate(key: string): any {
        let translation = key;
        if (this._translations[this._currentLang] && _.get(this._translations[this.currentLang], key)) {
            return _.get(this._translations[this.currentLang], key);
        }
        return translation;
    }

    public instant(key: string){
        return this.translate(key);
    }
}