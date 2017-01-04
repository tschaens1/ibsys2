import { Router, NavigationExtras } from '@angular/router';
import { LoginService } from './../login/login.service';
import { Component, AfterViewInit, OnInit, Input } from '@angular/core';
import { SearchService } from './../search/search.service';

require('./../../styles/styles.global.scss');
// require('./../../images/favicon.ico');

@Component({
    selector: 'start',
    templateUrl: './start.component.html',
    styleUrls: ['./start.component.scss']
})
export class StartComponent implements AfterViewInit, OnInit {
    searchResults: any[] = [];
    searchTerm: any = '';

    @Input('route') route: string;

    constructor(
        private loginService: LoginService,
        private router: Router,
        private searchService: SearchService) { }

    ngOnInit() {
        // hide the sidenav if the route changes
        this.router.events.subscribe(e => {
            if ($(window).width() < 800) {
                this.sideNavHide();
            }
        });

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

    // show search results in the results box while typings sth into the search input
    onSearchKeyUp() {
        this.searchResults = this.searchService.getResults(this.searchTerm, 10);
    }

    // navigate to the search component after pressing 'Enter'-Key
    onSearchEnter() {
        // if no search result entry has the class 'focused'        
        let navigationExtras: NavigationExtras = {
            queryParams: { q: this.searchTerm }
        };
        this.router.navigate(['/app/search'], navigationExtras);
        this.searchTerm = '';
        this.hideSearchResults();
    }

    // hide the search results on blur (input loses focus)
    hideSearchResults() {
        setTimeout(() => {
            this.searchResults = [];
            this.searchTerm = '';
        }, 500);
    }

    // navigates to a page if user clicked on a search result
    searchNavigateTo(route: any, fragment?: string) {
        if (fragment === undefined) {
            this.router.navigate(route);
            this.searchTerm = '';
        } else {
            let navigationExtras: NavigationExtras = {
                fragment: fragment
            };
            this.router.navigate(route, navigationExtras);
        }
    }

    ngAfterViewInit() {
        $('.app-content').removeClass('small-side-enabled');

        // set event handler to hide sidenav if user clicks on the dimmer
        $('.dimmer').on('click', () => {
            this.sideNavHide();
        });

        // show the sidenav on a higher resolution
        if ($(window).width() > 1200) {
            this.sideNavShow();
        }
    }
}