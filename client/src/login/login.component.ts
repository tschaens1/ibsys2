import { Component, AfterViewInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { User } from './user';

declare var jQuery: any;

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styles: [ require('./login.component.scss') ] 
})
export class LoginComponent implements AfterViewInit{    
    name: string;
    password: string;

    constructor(private loginService: LoginService) { }

    submitLogin(){        
        this.loginService.login();
    }

    ngAfterViewInit(){        
    }
}