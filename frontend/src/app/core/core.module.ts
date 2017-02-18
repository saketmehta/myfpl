import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { SharedModule } from '../shared/shared.module';
import { LoggerService } from './logger.service';
import { AlertService } from './alert.service';
import { UserService } from './user.service';
import { FixtureService } from './fixture.service';
import { TeamService } from './team.service';
import { AuthGuardService } from './auth-guard.service';
import { BootstrapService } from './bootstrap.service';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { AlertComponent } from './alert/alert.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  imports: [
    SharedModule,
    RouterModule
  ],
  declarations: [
    NavbarComponent,
    AlertComponent,
    LoginFormComponent,
    RegisterComponent
  ],
  exports: [
    NavbarComponent,
    AlertComponent,
    LoginFormComponent,
    RegisterComponent
  ],
  providers: [
    LoggerService,
    AlertService,
    UserService,
    AuthGuardService,
    BootstrapService,
    TeamService,
    FixtureService
  ]
})
export class CoreModule { }
