import { Component } from '@angular/core';
import { LoginService } from '../login/login.service';
// let styles = String(require('./navigation.component.scss'));

declare var jQuery:any;

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styles: [ require('./navigation.component.scss') ] 
})
export class Navigation { 
    constructor(private loginService: LoginService){ }
    
    logout() {
        this.loginService.logout();
        console.log('nav clicked on logout');
    }

    ngAfterViewInit() {
        jQuery(".button-collapse").sideNav();
    }
}
