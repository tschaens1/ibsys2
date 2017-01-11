import { AboutComponent } from '../about/about.component';
import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashBoardComponent } from '../dashboard/dashboard.component';
import { LoginComponent } from '../login/login.component';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { StartComponent } from '../start/start.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { SettingsComponent } from './../settings/settings.component';
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
import { PlanningGuard } from './../planning/planning.guard';
import { PreparePlanningGuard } from './../planning/preparePlanning.guard';
import { PageNotFoundComponent } from './../pagenotfound/pagenotfound.component';


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
                redirectTo: 'start'
            },
            {
                path: 'start',
                component: WelcomeComponent
            },
            {
                path: 'dashboard',
                component: DashBoardComponent,
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
                path: 'about',
                component: AboutComponent,
            },
            {
                path: 'planning',
                component: PlanningComponent,
                children: [
                    {
                        path: '',
                        component: PlanningOverviewComponent,
                        canActivate: [PreparePlanningGuard],
                    },
                    {
                        path: 'production',
                        component: PlanningProductionComponent,
                        canActivate: [PlanningGuard],
                    },
                    {
                        path: 'material',
                        component: PlanningMaterialComponent,
                        canActivate: [PlanningGuard],
                    },
                    {
                        path: 'purchasing',
                        component: PlanningPurchasingComponent,
                        canActivate: [PlanningGuard],
                    },
                    {
                        path: 'capacity',
                        component: PlanningCapacityComponent,
                        canActivate: [PlanningGuard],
                    },
                    {
                        path: 'inserts',
                        component: PlanningInsertsComponent,
                        canActivate: [PlanningGuard],
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
