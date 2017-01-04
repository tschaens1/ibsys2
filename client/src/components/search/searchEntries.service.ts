import { OnInit, Injectable } from '@angular/core';
import { SettingsService } from './../settings/settings.service';
import { basic_claims_data_DE } from './../translate/basic_claims_data_de';
import { basic_claims_data_EN } from './../translate/basic_claims_data_en';
import { searchEntriesDE } from './../translate/searchEntries-de';
import { searchEntriesEN } from './../translate/searchEntries-en';
import { SearchEntry } from './searchEntry.interface';

@Injectable()
export class SearchEntries {
    searchEntries: SearchEntry[];

    constructor(private settings: SettingsService) {
        this.searchEntries = this.getEntries();
    }

    /**
     * reverse the id of the basic claim data
     */
    reverse(s: string): string {
        return s.substr(s.length - 1, 1) + s.substr(0, s.length - 1);
    }

    /**
     * remove the whitespace of the ids of some data entries
     */
    noWhiteSpace(s: string): string {
        return s.replace(/ /g, '');
    }

    getEntries() {
        if (this.settings.getLanguage() === 'de') {
            this.searchEntries = [
                ...searchEntriesDE,
                ...basic_claims_data_DE.map(entry => {
                    return {
                        id: Math.random(),
                        name: '[' + this.reverse(this.noWhiteSpace(entry.id)) + '] ' + entry.Description,
                        route: ['/app/parts'],
                        fragment: this.reverse(this.noWhiteSpace(entry.id)),
                        tags: [this.reverse(this.noWhiteSpace(entry.id)), entry.Description],
                        description: entry.Description,
                    }
                })];
        } else {
            this.searchEntries = [
                ...searchEntriesEN,
                ...basic_claims_data_EN.map(entry => {
                    return {
                        id: Math.random(),
                        name: '[' + this.reverse(this.noWhiteSpace(entry.id)) + '] ' + entry.Description,
                        route: ['/app/parts'],
                        fragment: this.reverse(this.noWhiteSpace(entry.id)),
                        tags: [this.reverse(this.noWhiteSpace(entry.id)), entry.Description],
                        description: entry.Description,
                    }
                })];
        }
        return this.searchEntries;
    }
}