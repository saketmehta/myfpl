import { AlertService } from './../../core/alert/alert.service';
import { LeagueService } from './../../core/services/league.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-join-league',
  templateUrl: './join-league.component.html',
  styleUrls: ['./join-league.component.css']
})
export class JoinLeagueComponent implements OnInit {
  inviteCode: string;

  constructor(private leagueService: LeagueService, private alertService: AlertService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.leagueService.join(this.inviteCode)
      .subscribe(league => {
        this.alertService.success('Yay! League joined!', true);
      });
  }
}
