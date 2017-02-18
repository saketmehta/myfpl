import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LeagueComponent } from './league.component';
import { AuthGuardService } from '../core/auth-guard.service';

const routes: Routes = [
  { path: 'league', component: LeagueComponent, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class LeagueRoutingModule { }
