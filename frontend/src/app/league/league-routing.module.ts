import { CreateLeagueComponent } from './create-league/create-league.component';
import { JoinLeagueComponent } from './join-league/join-league.component';
import { MyLeaguesComponent } from './my-leagues/my-leagues.component';
import { AuthGuardService } from './../core/services/auth-guard.service';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LeagueComponent } from './league.component';

const routes: Routes = [
  {
    path: 'leagues', component: LeagueComponent, canActivate: [AuthGuardService],
    children: [
      { path: '', redirectTo: 'my-leagues', pathMatch: 'full' },
      { path: 'my-leagues', component: MyLeaguesComponent },
      { path: 'create-league', component: CreateLeagueComponent },
      { path: 'join-league', component: JoinLeagueComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class LeagueRoutingModule { }
