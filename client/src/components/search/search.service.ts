import { Injectable } from '@angular/core';

interface SearchEntry {
    id: number;
    name: string;
    route: string[];
    tags: string[];
    description: string;
}

@Injectable()
export class SearchService {
    // the search results are routes on which you can navigate
    private searchEntries: SearchEntry[] = [
        {
            id: 1,
            name: 'Dashboard',
            route: ['/app/dashboard'],
            tags: ['Start', 'Home', 'Dashboard'],
            description: 'Show statistics for your planning'
        },
        {
            id: 2,
            name: 'Warehouse',
            tags: ['Warehouse'],
            route: ['/app/warehouse'],
            description: 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'
        },
        {
            id: 3,
            name: 'Profile',
            tags: ['Profile', 'User', 'Account'],
            route: ['/app/profile'],
            description: 'Profile details and settings'
        },
        {
            id: 4,
            name: 'Settings',
            tags: ['Settings'],
            route: ['/app/settings'],
            description: 'Go to the settings'
        }

    ]

    getResults(term: string): Array<any> {
        let results = [];
        if (term.trim().length === 0) {
            return results;
        } else {
            this.searchEntries.forEach(entry => {
                if (entry.name.toLowerCase().includes(term.trim().toLowerCase()) ||
                    entry.tags.filter(e => e.toLowerCase().includes(term.trim().toLowerCase())).length > 0) {
                    results.push(entry);
                }
            });
        }
        return results;
    }
}