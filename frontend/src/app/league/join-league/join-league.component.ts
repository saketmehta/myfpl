import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-join-league',
  templateUrl: './join-league.component.html',
  styleUrls: ['./join-league.component.css']
})
export class JoinLeagueComponent implements OnInit {
  @Output() onJoin = new EventEmitter<number>();
  joinLeagueModel = {
    inviteCode: <string>null
  };

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    //
  }
}
