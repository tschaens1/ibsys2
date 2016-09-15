import { Component } from '@angular/core';
import { LoginService } from '../login/login.service';
import { User } from './user';

// let styles = String(require('./login.component.scss'));

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styles: [ require('./login.component.scss') ] 
})
export class LoginComponent {    
    name: string;
    password: string;

    constructor(private loginService: LoginService) { }

    submitLogin(){        
        this.loginService.login();
    }
}