import { Router } from '@angular/router';
import { LoginService } from './../login/login.service';
import { Component, AfterViewInit, OnInit } from '@angular/core';
import { SearchService } from './../search/search.service';

require('./../../styles/styles.global.scss');
require('./../../images/favicon.ico');

@Component({
    selector: 'start',
    templateUrl: './start.component.html',
    styleUrls: ['./start.component.scss']
})
export class StartComponent implements AfterViewInit, OnInit {
    searchResults: any[] = [];
    searchTerm: any = '';

    constructor(private loginService: LoginService, private router: Router, private searchService: SearchService) {
    }

    ngOnInit() {
        // hide the sidenav if the route changes
        this.router.events.subscribe(e => {
            if ($(window).width() < 800) {
                this.sideNavHide();
            }
        })
    }

    logout() {
        this.loginService.logout();
    }

    sideNavToggle() {
        $('.app-content').toggleClass('small-side-enabled');
        $('.app-navbar').toggleClass('small-side-enabled');
        $('.app-sidenav').toggleClass('show');
    }

    sideNavHide() {
        $('.app-content').removeClass('small-side-enabled');
        $('.app-navbar').removeClass('small-side-enabled');
        $('.app-sidenav').removeClass('show');
    }

    sideNavShow() {
        $('.app-content').addClass('small-side-enabled');
        $('.app-navbar').addClass('small-side-enabled');
        $('.app-sidenav').addClass('show');
    }

    onResize(event: any) {
        // hide the sidenav if the user resizes the window on mobile devices
        if ($(window).width() < 800) {
            this.sideNavHide();
        }
    }

    // show search results in the results box
    onSearchKeyUp() {
        this.searchResults = this.searchService.getResults(this.searchTerm);
    }

    onSearchEnter() {
        this.router.navigate(['/app/search', { term: this.searchTerm }])        
        this.searchTerm = '';
        this.hideSearchResults();
    }

    // hide the search results on blur
    hideSearchResults() {
        setTimeout(()=>{
            this.searchResults = [];
        }, 500);
    }

    searchNavigateTo(route: any){
        this.router.navigate(route);
        this.searchTerm = '';
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