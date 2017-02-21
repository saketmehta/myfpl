import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-league',
  templateUrl: './league.component.html',
  styleUrls: ['./league.component.css']
})
export class LeagueComponent implements OnInit {
  view = 0;

  constructor() { }

  ngOnInit() {
  }

  onViewChange(view: number) {
    this.view = view;
  }
}
