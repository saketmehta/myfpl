import { HttpModule } from '@angular/http';
import { AuthService } from './services/auth.service';
import { FixtureService } from './services/fixture.service';
import { LeagueService } from './services/league.service';
import { TeamService } from './services/team.service';
import { BootstrapService } from './services/bootstrap.service';
import { AuthGuardService } from './services/auth-guard.service';
import { UserService } from './services/user.service';
import { AlertModule } from './alert/alert.module';
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { AlertComponent } from './alert/alert.component';

@NgModule({
  imports: [
    RouterModule,
    HttpModule,
    SharedModule,
    AlertModule
  ],
  declarations: [
  ],
  exports: [
    AlertComponent
  ],
  providers: [
    UserService,
    AuthGuardService,
    BootstrapService,
    TeamService,
    FixtureService,
    LeagueService,
    AuthService
  ]
})
export class CoreModule {

  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }

}
