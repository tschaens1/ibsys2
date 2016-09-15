import { Component, AfterViewInit } from '@angular/core';
import { LoginService } from '../login/login.service';
// let styles = String(require('./navigation.component.scss'));

declare var jQuery: any;

@Component({
    selector: 'navigation',
    templateUrl: './navigation.component.html',
    styles: [require('./navigation.component.scss')]
})
export class Navigation implements AfterViewInit {
    constructor(private loginService: LoginService) { }

    logout() {
        this.loginService.logout();
        console.log('nav clicked on logout');
    }

    ngAfterViewInit() {
        jQuery('.button-collapse').sideNav();
        jQuery('.dropdown-button').dropdown({
            inDuration: 300,
            outDuration: 225,
            constrain_width: false, // Does not change width of dropdown to that of the activator
            hover: true, // Activate on hover
            gutter: 0, // Spacing from edge
            belowOrigin: false, // Displays dropdown below the button
            alignment: 'left' // Displays dropdown with edge aligned to the left of button
        }
        );
    }
}
