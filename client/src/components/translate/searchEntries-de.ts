import { SearchEntry } from './../search/searchEntry.interface';

let searchEntriesDE: SearchEntry[] = [
    {
        id: 1,
        name: 'Dashboard',
        route: ['/app/dashboard'],
        tags: ['Start', 'Home', 'Dashboard'],
        description: 'Statistiken zur Planung anzeigen'
    },
    {
        id: 2,
        name: 'Lager',
        tags: ['Lager'],
        route: ['/app/warehouse'],
        description: 'Lagerhaus Übersichtsseite',
    },
    {
        id: 3,
        name: 'Profile',
        tags: ['Profile', 'Benutzer', 'Konto'],
        route: ['/app/profile'],
        description: 'Profileinstellungen'
    },
    {
        id: 4,
        name: 'Einstellungen',
        tags: ['Einstellungen', 'Sprache', 'Internationalisierung', 'i18n'],
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
        tags: ['Materialplanung', 'planung'],
        route: ['/app/planning/material'],
        description: 'Materialplanung durchführen'
    },
    {
        id: 7,
        name: 'Produktionsplanung',
        tags: ['Produktionsplanung', 'planung'],
        route: ['/app/planning/production'],
        description: 'Produktionsplanung durchführen'
    },
];

export default searchEntriesDE;