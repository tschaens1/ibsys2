import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { SettingsService } from './../settings/settings.service';

@Injectable()
export class LoginService {
    private loggedIn = false;

    constructor(private router: Router, private settingsService: SettingsService) {
        this.loggedIn = !!this.settingsService.getLoginBehaviour();        
    }

    login(object: any) {
        if (object.options.stayLoggedIn) {
            this.settingsService.setLoginBehaviour(true);
        }
        this.loggedIn = true;
        this.router.navigate(['/app']);
    }

    logout() {
        this.settingsService.setLoginBehaviour(false);
        this.loggedIn = false;
        this.router.navigate(['/login']);
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}