import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { StartComponent } from '../start/start.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: StartComponent        
    },
    {
        path: 'start',
        component: StartComponent    
    },
    {
        path: '**',
        redirectTo: '/login'
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
