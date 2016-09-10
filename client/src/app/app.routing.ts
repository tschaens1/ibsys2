import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { LoginGuard } from '../login/login.guard';
import { LogoutGuard } from '../login/logout.guard';
import { StartComponent } from '../start/start.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [LogoutGuard]    
    },
    {
        path: 'start',
        component: StartComponent,
        canActivate: [LoginGuard]
    },
    {
        path: '**',
        redirectTo: '/login'
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
