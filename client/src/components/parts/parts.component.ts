import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SearchService } from './../search/search.service';
import { basic_claims_data_DE } from './../translate/basic_claims_data_de';
import { basic_claims_data_EN } from './../translate/basic_claims_data_en';
import { Part } from './part.interface';
import { TranslationService } from './../translate/translate.service';
import * as _ from 'lodash';

@Component({
    selector: 'parts',
    templateUrl: './parts.component.html',
    styleUrls: ['./parts.component.scss']
})
export class PartsComponent {
    data: Part[];
    filteredData: Part[];
    searchTerm: string;

    constructor(private translationService: TranslationService, private route: ActivatedRoute) {
        // select the language specific basic data
        this.data = this.translationService.currentLanguage === 'de' ? basic_claims_data_DE : basic_claims_data_EN;
        this.filteredData = this.data;

        this.route.fragment.subscribe(value => {
            if (value === undefined) return;
            this.searchTerm = value;
            this.onSearchKeyUp();
        })
    }

    onSearchKeyUp() {
        this.filteredData = this.data.filter((item: Part) => {
            return (item.Description.trim().toLowerCase().includes(this.searchTerm.trim().toLowerCase())
                || this.reverse(this.noWhiteSpace(item.id).trim().toLowerCase()).includes(this.searchTerm.trim().toLowerCase()));
        });
    }

    getWorkstations(id: string): string[] {
        let idsOfWorkStations: string[];

        for (let z = 0; z < this.data.length; z++) {
            if (this.data[z].id === id) {
                let keys = Object.keys(this.data[z]).filter(key => {
                    return (key.indexOf('ProcessingTimeWS') !== -1 && _.get(this.data[z], key) > 0)
                }).map(key => {
                    return key.slice(key.indexOf('WS') + 2);
                });
                idsOfWorkStations = keys;
                break;
            }
        }

        return idsOfWorkStations;
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
}