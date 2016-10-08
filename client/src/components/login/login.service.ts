import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class LoginService {
    private loggedIn = false;

    constructor(private router: Router) {
        this.loggedIn = !!localStorage.getItem('loggedIn');
    }

    login(object: any) {
        if (object.options.stayLoggedIn) {
            localStorage.setItem('loggedIn', 'true');
        }
        this.loggedIn = true;
        this.router.navigate(['/app']);
    }

    logout() {
        localStorage.removeItem('loggedIn');
        this.loggedIn = false;
        this.router.navigate(['/login']);
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}