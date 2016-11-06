import { Injectable } from '@angular/core';
import { TranslationService } from './../translate/translate.service';
import { SearchEntries } from './searchEntries.service';

@Injectable()
export class SearchService {

    constructor(private translationService: TranslationService, private searchEntries: SearchEntries) { }

    /**
     * Get the search results for a given search term
     * @param {string} term - The search term to filter the result
     * @param {number|undefined} amount - Amount of the returned results
     * @returns {Array} - Search results
     */
    getResults(term: string, amount?: number): Array<any> {
        let results = [];
        if (term.trim().length === 0) {
            return results;
        } else {
            let searchEntries = this.searchEntries.getEntries();
            searchEntries.forEach(entry => {
                if ((
                    // searchterm is part of the entry name
                    entry.name.toLowerCase().indexOf(term.trim().toLowerCase()) === 0 ||

                    // searchterm is part of one of the tags
                    entry.tags.filter(e => e.toLowerCase().indexOf(term.trim().toLowerCase()) === 0).length > 0) &&

                    // only return the wanted amount of results
                    results.length <= (amount === undefined ? Number.MAX_SAFE_INTEGER : amount)) {

                    // add the entry to the results array if the condition is matching
                    results.push(entry);
                }
            });
        }
        return results;
    }
}