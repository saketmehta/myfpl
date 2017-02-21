import { LeagueService } from './../../core/services/league.service';
import { League } from './../../core/models/league';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-leagues',
  templateUrl: './my-leagues.component.html',
  styleUrls: ['./my-leagues.component.css']
})
export class MyLeaguesComponent implements OnInit {
  myLeagues: League[];

  constructor(private leagueService: LeagueService) { }

  ngOnInit() {
    this.leagueService.fetch()
    .subscribe(leagues => this.myLeagues = leagues);
  }

}
