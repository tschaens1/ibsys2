import { Component } from '@angular/core';
import { LoginService } from '../login/login.service';
let styles = String(require('./navigation.component.css'));

@Component({
  selector: 'navigation',
  templateUrl: './navigation.component.html',
  styles: [styles]  
})
export class Navigation { 
    constructor(private loginService: LoginService){ }
    
    logout() {
        this.loginService.logout();
        console.log('nav clicked on logout');
    }
}
