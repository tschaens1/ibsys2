import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { StartComponent } from '../start/start.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [LoggedOutGuard]
    },
    {
        path: 'app',
        canActivate: [LoggedInGuard],
        children: [{
            path: 'start',
            component: StartComponent
        },
        {
            path: 'warehouse',
            component: WarehouseComponent,
        },
        {
            path: '**',
            redirectTo: 'start'
        }]
    },
    {
        path: '**',
        redirectTo: '/login'
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
