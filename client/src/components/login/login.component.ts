import { Component, OnInit, AfterViewInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { User } from './user';
import { TranslationService } from './../translate/translate.service';
import { SettingsService } from './../settings/settings.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, AfterViewInit {
    name: string;
    password: string;
    stayLoggedIn: boolean = true;

    currentLanguage: string;

    constructor(private loginService: LoginService, private settingsService: SettingsService) {
    }

    submitLogin() {
        this.loginService.login({
            username: '',
            password: '',
            options: {
                stayLoggedIn: this.stayLoggedIn
            }
        });
    }

    ngOnInit() {
        this.currentLanguage = this.settingsService.getLanguage();
    }

    ngAfterViewInit() {
        $('.dropdown-button').dropdown({
            inDuration: 300,
            outDuration: 225,
            constrain_width: false, // Does not change width of dropdown to that of the activator
            hover: false, // Activate on hover
            gutter: 0, // Spacing from edge
            belowOrigin: false, // Displays dropdown below the button
            alignment: 'left' // Displays dropdown with edge aligned to the left of button
        }
        );
    }

    changeLanguage(language: string) {
        this.settingsService.setLanguage(language);
    }
}