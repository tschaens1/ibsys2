import { PageNotFoundComponent } from './../pagenotfound/pagenotfound.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { StartComponent } from '../start/start.component';
import { DashBoardComponent } from '../dashboard/dashboard.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';

const appRoutes: Routes = [
    {
        path: '',
        redirectTo: 'app',
        pathMatch: 'full',
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [LoggedOutGuard]
    },
    {
        path: 'app',
        canActivate: [LoggedInGuard],
        component: StartComponent,
        children: [
            {
                path: '',
                component: DashBoardComponent
            },
            {
                path: 'start',
                redirectTo: '/app'
            },
            {
                path: 'warehouse',
                component: WarehouseComponent
            },
        ]
    },
    {
        path: '**',
        redirectTo: 'pagenotfound',
    },
    {
        path: 'pagenotfound',
        component: PageNotFoundComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
