import { PageNotFoundComponent } from './../pagenotfound/pagenotfound.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from '../login/login.component';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { StartComponent } from '../start/start.component';
import { SettingsComponent } from './../settings/settings.component';
import { DashBoardComponent } from '../dashboard/dashboard.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';
import { SearchComponent } from './../search/search.component';
import { HelpComponent } from './../help/help.component';
import { PlanningComponent } from './../planning/planning.component';
import { PlanningOverviewComponent } from './../planning_overview/planning_overview.component';
import { PlanningProductionComponent } from './../planning_production/planning_production.component';
import { PlanningMaterialComponent } from './../planning_material/planning_material.component';
import { PlanningPurchasingComponent } from './../planning_purchasing/planning_purchasing.component';
import { PlanningInsertsComponent } from './../planning_inserts/planning_inserts.component';
import { PlanningCapacityComponent } from './../planning_capacity/planning_capacity.component';
import { UploadComponent } from './../upload/upload.component';
import { PartsComponent } from './../parts/parts.component';


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
                redirectTo: 'dashboard'
            },
            {
                path: 'dashboard',
                component: DashBoardComponent
            },
            {
                path: 'start',
                redirectTo: 'dashboard'
            },
            {
                path: 'warehouse',
                component: WarehouseComponent
            },
            {
                path: 'search',
                component: SearchComponent
            },
            {
                path: 'settings',
                component: SettingsComponent
            },
            {
                path: 'help',
                component: HelpComponent
            },
            {
                path: 'planning',
                component: PlanningComponent,
                children: [
                    {
                        path: '',
                        redirectTo: 'production'
                    },                    
                    {
                        path: 'production',
                        component: PlanningProductionComponent
                    },
                    {
                        path: 'material',
                        component: PlanningMaterialComponent
                    },
                    {
                        path: 'purchasing',
                        component: PlanningPurchasingComponent
                    },
                    {
                        path: 'capacity',
                        component: PlanningCapacityComponent
                    },
                    {
                        path: 'inserts',
                        component: PlanningInsertsComponent
                    },    
                ]
            },            
                    
            {
                path: 'upload',
                component: UploadComponent
            },
            {
                path: 'parts',
                component: PartsComponent
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
