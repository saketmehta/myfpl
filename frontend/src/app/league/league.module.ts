import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LeagueRoutingModule } from './league-routing.module';
import { LeagueComponent } from './league.component';

@NgModule({
  imports: [
    CommonModule,
    LeagueRoutingModule
  ],
  declarations: [LeagueComponent]
})
export class LeagueModule { }
