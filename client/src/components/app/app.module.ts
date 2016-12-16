import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WarehouseComponent } from '../warehouse/warehouse.component';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login/login.service';
import { LoggedInGuard } from '../login/logged-in.guard';
import { LoggedOutGuard } from '../login/logged-out.guard';
import { PageNotFoundComponent } from './../pagenotfound/pagenotfound.component';
import { StartComponent } from '../start/start.component';
import { SettingsComponent } from './../settings/settings.component';
import { DashBoardComponent } from '../dashboard/dashboard.component';
import { SearchComponent } from './../search/search.component';
import { SearchService } from './../search/search.service';
import { SearchEntries } from './../search/searchEntries.service';
import { PlanningComponent } from './../planning/planning.component';
import { HelpComponent } from './../help/help.component';
import { PlanningService } from './../planning/planning.service';
import { PlanningGuard } from './../planning/planning.guard';
import { PreparePlanningGuard } from './../planning/preparePlanning.guard';
import { PlanningOverviewComponent } from './../planning_overview/planning_overview.component';
import { PlanningProductionComponent } from './../planning_production/planning_production.component';
import { PlanningMaterialComponent } from './../planning_material/planning_material.component';
import { PlanningPurchasingComponent } from './../planning_purchasing/planning_purchasing.component';
import { PlanningInsertsComponent } from './../planning_inserts/planning_inserts.component';
import { PlanningCapacityComponent } from './../planning_capacity/planning_capacity.component';
import { UploadComponent } from './../upload/upload.component';
import { FileUploadComponent } from './../upload/fileupload.component';
import { SettingsService } from './../settings/settings.service';
import { PartsComponent } from './../parts/parts.component';
import { UploadService } from './../upload/upload.service';
import { ArticleService } from './../RESTServices/ArticleService';

import { NouisliderModule } from 'ng2-nouislider';
import { ToastModule, ToastOptions } from 'ng2-toastr/ng2-toastr';

import { TRANSLATION_PROVIDERS } from './../translate/translations';
import { TranslatePipe } from './../translate/translate.pipe';
import { TranslationService } from './../translate/translate.service';

import { routing } from './app.routing';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import { MaterialModule } from '@angular/material';
import { ChartModule } from 'angular2-highcharts';

import { PerfectScrollbarModule, PerfectScrollbarConfigInterface } from 'angular2-perfect-scrollbar';

const PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

let toastOptions: ToastOptions = new ToastOptions({
  animate: 'fade',
  toastLife: 3000,
  positionClass: 'toast-bottom-right',
});

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    ChartsModule,
    ChartModule,
    NouisliderModule,
    ToastModule.forRoot(toastOptions),
    MaterialModule.forRoot(),
    PerfectScrollbarModule.forRoot(PERFECT_SCROLLBAR_CONFIG),
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    StartComponent,
    SettingsComponent,
    HelpComponent,
    WarehouseComponent,
    DashBoardComponent,
    SearchComponent,
    PageNotFoundComponent,
    TranslatePipe,
    PlanningComponent,
    PlanningOverviewComponent,
    PlanningProductionComponent,
    PlanningMaterialComponent,
    PlanningPurchasingComponent,
    PlanningInsertsComponent,
    PlanningCapacityComponent,
    UploadComponent,
    FileUploadComponent,
    PartsComponent,
  ],
  providers: [
    LoginService,
    LoggedInGuard,
    LoggedOutGuard,
    PlanningGuard,
    PreparePlanningGuard,
    SearchService,
    SearchEntries,
    SettingsService,
    UploadService,
    ArticleService,
    PlanningService,
    TRANSLATION_PROVIDERS,
    TranslationService,
    { provide: 'ApiEndpoint', useValue: 'http://localhost:8080' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
