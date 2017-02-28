import { LeagueService } from './../../core/services/league.service';
import { League } from './../../core/models/league';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-leagues',
  templateUrl: './my-leagues.component.html',
  styleUrls: ['./my-leagues.component.css']
})
export class MyLeaguesComponent implements OnInit {
  myLeagues: League[] = [
    {
      id: 'sfsffsdfs',
      leagueId: 232,
      name: 'My League ABCD',
      adminUserId: 1000,
      capacity: 5,
      users: [1000],
      inviteCode: 'ASUDHASI'
    }, {
      id: 'uvhifv',
      leagueId: 343,
      name: 'BLAH BLAH ABCD',
      adminUserId: 1001,
      capacity: 4,
      users: [1000, 1001],
      inviteCode: 'YDGSUYG'
    }
  ];

  constructor(private leagueService: LeagueService) { }

  ngOnInit() {
    // this.leagueService.fetch()
    //   .subscribe(leagues => this.myLeagues = leagues);
  }

}
