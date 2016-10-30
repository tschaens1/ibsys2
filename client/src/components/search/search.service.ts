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
            description: 'Go to the warehouse overview',
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
        },
        {
            id: 5,
            name: 'Planning',
            tags: ['planning', 'overview'],
            route: ['/app/planning'],
            description: 'Go to the planning'
        },
        {
            id: 6,
            name: 'Material planning',
            tags: ['Material planning', 'planning'],
            route: ['/app/planning/material'],
            description: 'Go to the material planning'
        },
        {
            id: 7,
            name: 'Production planning',
            tags: ['production planning', 'planning'],
            route: ['/app/planning/material'],
            description: 'Go to the production planning'
        },
    ]

    getResults(term: string, amount?: number): Array<any> {
        let results = [];
        if (term.trim().length === 0) {
            return results;
        } else {
            this.searchEntries.forEach(entry => {
                if ((entry.name.toLowerCase().indexOf(term.trim().toLowerCase())===0 ||
                    entry.tags.filter(e => e.toLowerCase().indexOf(term.trim().toLowerCase())===0).length > 0) &&
                    results.length <= (amount===undefined?Number.MAX_SAFE_INTEGER:amount)) {
                    results.push(entry);
                }
            });
        }
        return results;
    }
}