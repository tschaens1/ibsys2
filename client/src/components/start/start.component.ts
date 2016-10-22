import { Router } from '@angular/router';
import { LoginService } from './../login/login.service';
import { Component, AfterViewInit, OnInit } from '@angular/core';

require('./../../styles/styles.global.scss');
require('./../../images/favicon.ico');

@Component({
    selector: 'start',
    templateUrl: './start.component.html',
    styleUrls: ['./start.component.scss']
})
export class StartComponent implements AfterViewInit, OnInit {
    constructor(private loginService: LoginService, private router: Router) {
    }

    ngOnInit() {
        // hide the sidenav if the route changes
        this.router.events.subscribe(e => {
            if ($(window).width() < 800) {
                this.sideNavHide();
            }
        })

        // initialize mmenu
        // jQuery(document).ready(function ($) {
        //     $("#my-menu").mmenu();
        // });        
    }

    logout() {
        this.loginService.logout();
    }

    sideNavToggle() {
        $('.app-content').toggleClass('small-side-enabled');
        $('.app-sidenav').toggleClass('show');
    }

    sideNavHide() {
        $('.app-content').removeClass('small-side-enabled');
        $('.app-sidenav').removeClass('show');
    }

    sideNavShow() {
        $('.app-content').addClass('small-side-enabled');
        $('.app-sidenav').addClass('show');
    }

    onResize(event: any) {
        // hide the sidenav if the user resizes the window on mobile devices
        if ($(window).width() < 800) {
            this.sideNavHide();
        }
    }

    ngAfterViewInit() {
        $('.app-content').removeClass('small-side-enabled');
        $('.dimmer').on('click', () => {
            this.sideNavHide();
        });
        if ($(window).width() > 1200) {
            this.sideNavShow();
        }
    }
}