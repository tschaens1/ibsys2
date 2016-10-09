import { LoginService } from './../login/login.service';
import { Component, AfterViewInit } from '@angular/core';

require('./../../styles/styles.global.scss');
require('./../../images/favicon.ico');

@Component({
    selector: 'start',
    templateUrl: './start.component.html',
    styleUrls: ['./start.component.scss']
})
export class StartComponent implements AfterViewInit{            
    constructor(private loginService: LoginService) {        
    }
    
    logout(){
        this.loginService.logout();
    }

    sideNavToggle(){        
        $('.app-content').toggleClass('small-side-enabled');
        $('.app-sidenav').toggleClass('show');
    }

    ngAfterViewInit(){        
        $('.app-content').removeClass('small-side-enabled');
    }
}