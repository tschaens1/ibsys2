import { SearchEntry } from './../search/searchEntry.interface';

export const searchEntriesEN: SearchEntry[] = [
    {
        id: 1,
        name: 'Start',
        route: ['/app/start'],
        tags: ['Start', 'Home'],
        description: 'Starting page of the application',
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
        tags: ['Settings', 'i18n', 'languages'],
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
    {
        id: 8,
        name: 'Capacity planning',
        tags: ['Capacity', 'Planung'],
        route: ['/app/planning/capacity'],
        description: 'Go to the capacity planning'
    },
    {
        id: 9,
        name: 'Inserts',
        tags: ['Inserts', 'Planung'],
        route: ['/app/planning/inserts'],
        description: 'Show insers for planning'
    },
    {
        id: 10,
        name: 'Parts',
        tags: ['purchase parts'],
        route: ['/app/parts'],
        description: 'Get information about all parts'
    },
    {
        id: 11,
        name: 'Help',
        tags: ['Help', 'FAQ'],
        route: ['/app/help'],
        description: 'Get some help'
    },
];