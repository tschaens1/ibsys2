import { Injectable } from '@angular/core';
import { TranslationService } from './../translate/translate.service';
import searchEntriesDE from './../translate/searchEntries-de';
import searchEntriesEN from './../translate/searchEntries-en';

@Injectable()
export class SearchService {

    constructor(private translationService: TranslationService) { }

    getResults(term: string, amount?: number): Array<any> {
        let results = [];
        if (term.trim().length === 0) {
            return results;
        } else {
            let searchEntries = this.translationService.currentLanguage === 'de' ? searchEntriesDE : searchEntriesEN;
            searchEntries.forEach(entry => {
                if ((entry.name.toLowerCase().indexOf(term.trim().toLowerCase()) === 0 ||
                    entry.tags.filter(e => e.toLowerCase().indexOf(term.trim().toLowerCase()) === 0).length > 0) &&
                    results.length <= (amount === undefined ? Number.MAX_SAFE_INTEGER : amount)) {
                    results.push(entry);
                }
            });
        }
        return results;
    }
}