import { Component, AfterViewInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { User } from './user';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styles: [require('./login.component.scss')]
})
export class LoginComponent implements AfterViewInit {
    name: string;
    password: string;
    stayLoggedIn: boolean = true;

    constructor(private loginService: LoginService) { }

    submitLogin() {
        this.loginService.login({
            username: '',
            password: '',
            options: {
                stayLoggedIn: this.stayLoggedIn
            }
        });
    }

    ngAfterViewInit() {
    }
}