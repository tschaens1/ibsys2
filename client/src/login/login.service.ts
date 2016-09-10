import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class LoginService {
    private loggedIn = false;

    constructor(private router: Router) {
        this.loggedIn = !!localStorage.getItem('loggedIn');
    }

    login(username: String, password: String) {
        localStorage.setItem('loggedIn', 'true');
        this.loggedIn = true;
        this.router.navigate(['/start']);
    }

    logout() {
        localStorage.removeItem('loggedIn');
        this.loggedIn = false;
        this.router.navigate(['/']);
    }

    isLoggedIn() {
        return this.loggedIn;
    }
}