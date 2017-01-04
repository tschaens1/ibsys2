import { ModalService } from './modal.service';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'modal',
    templateUrl: './modal.component.html',
    styleUrls: ['./modal.component.scss']
})
export class ModalComponent {
    constructor(private modalService: ModalService) { }

    clickDimmer() {
        if (!this.modalService.getLocked()) {
            this.modalService.hideModal();
        }
    }

    confirm() {
        this.modalService.runCallback();
        this.modalService.hideModal();
    }

    cancelModal() {
        this.modalService.hideModal();
    }
}