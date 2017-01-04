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

    /**
     * Open a modal.
     * 
     * @param {string} text The text inside the modal. 
     * This text should contain the information or the message for the user.
     * @param {string} title The title of the modal.
     * @param {boolean} locked The locked attribute provides the possibility to force 
     * the user to click the close button and not the dimmer in the background to hide the modal.
     * @param {Function} callback This callback will be performed when the user confirmed the modal.
     * If the user presses the 'cancel'-button the callback will not be called.
     */
    openModal(text: string, title?: string, locked?: boolean, callback?: Function) {
        this.show = true;
        this.currentTitle = title || 'Info';
        this.currentText = text;
        this.locked = locked || false;
        this.callback = callback || undefined;
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