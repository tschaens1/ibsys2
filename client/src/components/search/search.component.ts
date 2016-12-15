import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationExtras, Params, Router } from '@angular/router';
import { SearchService } from './../search/search.service';

@Component({
    selector: 'search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
    searchTerm: string;
    searchResults: any[];

    constructor(private router: Router, private route: ActivatedRoute, private searchService: SearchService) {
        this.searchTerm = 'sample search';
    }

    ngOnInit() {
        this.router.events.subscribe(e => {
            this.loadSearch();
        });

        this.loadSearch();
    }

    loadSearch() {
        this.route.queryParams.forEach((params: Params) => {
            this.searchTerm = params['q'] || 'nothing';
        });

        // load the search results
        this.searchResults = this.searchService.getResults(this.searchTerm);
    }

    // searchNavigateTo(route: any) {
    //     console.log(route);
    //     this.router.navigate(route);
    //     this.searchTerm = '';
    // }

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
}