import { SearchEntry } from './../search/searchEntry.interface';

let searchEntriesDE: SearchEntry[] = [
    {
        id: 1,
        name: 'Dashboard',
        route: ['/app/dashboard'],
        tags: ['Start', 'Home'],
        description: 'Statistiken zur Planung anzeigen'
    },
    {
        id: 2,
        name: 'Lager',
        tags: [],
        route: ['/app/warehouse'],
        description: 'Lagerhaus Übersichtsseite',
    },
    {
        id: 3,
        name: 'Profile',
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
        name: 'Produktionsplanung',
        tags: ['Planung'],
        route: ['/app/planning/production'],
        description: 'Produktionsplanung durchführen'
    },
    {
        id: 8,
        name: 'Eigenerzeugnisse',
        tags: ['Teile'],
        route: ['/app/parts'],
        fragment: 'in_house_parts',
        description: 'Liste der Eigenerzeugnisse'
    },
    {
        id: 9,
        name: 'Kaufteile',
        tags: ['Teile'],
        route: ['/app/parts'],
        fragment: 'purchase_parts',
        description: 'Liste der Kaufteile'
    },
];

export default searchEntriesDE;