import { LoginService } from './../login/login.service';
import { Component } from '@angular/core';

require('./../../styles/styles.global.scss');
require('./../../images/favicon.ico');

@Component({
    selector: 'start',
    templateUrl: './start.component.html',
    styles: [require('./start.component.scss')]
})
export class StartComponent {    
    openedSideNav:boolean;    
    
    constructor(private loginService: LoginService) {
        this.openedSideNav = true;   
    }

    get loggedIn(){
        return this.loginService.isLoggedIn();
    }

    setVar(){
        this.openedSideNav = false;
    }

    logout(){
        this.loginService.logout();
    }
}