import { SearchEntry } from './../search/searchEntry.interface';

export const searchEntriesEN: SearchEntry[] = [
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
        name: 'Parts',
        tags: ['purchase parts'],
        route: ['/app/parts'],        
        description: 'Get information about all parts'
    }, 
];