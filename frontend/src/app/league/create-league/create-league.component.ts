import { AlertService } from './../../core/alert/alert.service';
import { LeagueService } from './../../core/services/league.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-create-league',
  templateUrl: './create-league.component.html',
  styleUrls: ['./create-league.component.css']
})
export class CreateLeagueComponent implements OnInit {
  @Output() onCreate = new EventEmitter<number>();
  leagueModel = {
    name: <string>null,
    capacity: <number>null,
    inviteCode: <string>null
  };

  constructor(private leagueService: LeagueService, private alertService: AlertService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.leagueService.create(this.leagueModel)
      .subscribe(league => {
        this.alertService.success('Yay! League created!', true);
        this.onCreate.emit(0);
      });
  }
}
