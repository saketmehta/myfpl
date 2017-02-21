import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { LeagueRoutingModule } from './league-routing.module';
import { LeagueComponent } from './league.component';
import { CreateLeagueComponent } from './create-league/create-league.component';
import { JoinLeagueComponent } from './join-league/join-league.component';
import { MyLeaguesComponent } from './my-leagues/my-leagues.component';

@NgModule({
  imports: [
    SharedModule,
    LeagueRoutingModule
  ],
  declarations: [
    LeagueComponent,
    CreateLeagueComponent,
    JoinLeagueComponent,
    MyLeaguesComponent
  ]
})
export class LeagueModule { }
