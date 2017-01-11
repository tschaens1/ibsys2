import { SearchEntry } from './../search/searchEntry.interface';
import { basic_claims_data_DE } from './basic_claims_data_de';

export const searchEntriesDE: SearchEntry[] = [
    {
        id: 1,
        name: 'Start',
        route: ['/app/start'],
        tags: ['Start', 'Home'],
        description: 'Startseite der Anwendung'
    },
    {
        id: 3,
        name: 'Profil',
        tags: ['Benutzer', 'Konto', 'Account'],
        route: ['/app/profile'],
        description: 'Profileinstellungen'
    },
    {
        id: 4,
        name: 'Einstellungen',
        tags: ['Sprache', 'Internationalisierung', 'i18n'],
        route: ['/app/settings'],
        description: 'Verwalten der Einstellungen'
    },
    {
        id: 5,
        name: 'Planung',
        tags: ['planung', 'übersicht'],
        route: ['/app/planning'],
        description: 'Planung durchführen'
    },
    {
        id: 6,
        name: 'Materialplanung',
        tags: ['Planung'],
        route: ['/app/planning/material'],
        description: 'Materialplanung durchführen'
    },
    {
        id: 7,
        name: 'Kapazitätsplanung',
        tags: ['Kapazität', 'Planung'],
        route: ['/app/planning/capacity'],
        description: 'Kapazitätsplanung durchführen'
    },
    {
        id: 8,
        name: 'Eingaben',
        tags: ['Eingaben', 'Planung'],
        route: ['/app/planning/inserts'],
        description: 'Eingaben zur Planung anzeigen'
    },
    {
        id: 9,
        name: 'Produktionsplanung',
        tags: ['Planung'],
        route: ['/app/planning/production'],
        description: 'Produktionsplanung durchführen'
    },
    {
        id: 10,
        name: 'Teile',
        tags: ['Kaufteile', 'Eigenerzeugnisse'],
        route: ['/app/parts'],
        description: 'Liste der Teile'
    },
    {
        id: 11,
        name: 'Hilfe',
        tags: ['Hilfe', 'FAQ'],
        route: ['/app/help'],
        description: 'Hilfe bekommen'
    },
    {
        id: 12,
        name: 'Impressum',
        tags: ['About', 'Über', 'Info', 'Impressum'],
        route: ['/app/about'],
        description: 'Über die Anwendung',
    },
];