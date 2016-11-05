import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SearchService } from './../search/search.service';
import { basic_claims_data_DE } from './../translate/master_data_DE';
import { basic_claims_data_EN } from './../translate/master_data_EN';
import { Part } from './part.interface';
import { TranslationService } from './../translate/translate.service';

@Component({
    selector: 'parts',
    templateUrl: './parts.component.html',
    styleUrls: ['./parts.component.scss']
})
export class PartsComponent {
    data: Part[];
    filteredData: Part[];
    searchTerm: string;

    constructor(private translationService: TranslationService) {
        // select the language specific basic data
        this.data = this.translationService.currentLanguage === 'de'? basic_claims_data_DE : basic_claims_data_EN;
        this.filteredData = this.data;
    }

    onSearchKeyUp() {
        this.filteredData = this.data.filter((item: Part) => {
            return (item.Description.trim().toLowerCase().includes(this.searchTerm.trim().toLowerCase()                )
                || this.reverse(this.noWhiteSpace(item.id).trim().toLowerCase()).includes(this.searchTerm.trim().toLowerCase()));
        });
    }

    private reverse(s: string): string {
        return s.split("").reverse().join("");
    }

    private noWhiteSpace(s: string): string{
        return s.replace(/ /g,'');
    }
}