import { TranslationService } from '../translate/translate.service';
import { Injectable } from '@angular/core';

@Injectable()
export class ModalService {
    private currentText: string;
    private currentTitle: string;
    private show: boolean;
    private locked: boolean;
    private callback: Function;

    constructor(private translate: TranslationService) { }

    openModal(text: string, title?: string, locked?: boolean, callback?: Function) {
        this.show = true;
        this.currentTitle = title || 'Info';
        this.currentText = text;
        this.locked = locked || false;
        this.callback = Function || undefined;
    }

    getLocked() {
        return this.locked;
    }

    hideModal() {
        this.show = false;
    }

    runCallback() {
        if (this.callback) this.callback();
    }
}