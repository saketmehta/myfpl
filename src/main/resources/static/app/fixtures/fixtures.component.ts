import {Component, OnInit} from "@angular/core";
import {FixtureService} from "./fixture.service";
import {Fixture} from "./fixture";
@Component({
  selector: 'my-fixtures',
  template: `
    <ul>
      <li *ngFor="let fixture of fixtures">
        <span class="badge">{{fixture.teamHome}}</span>
        <span>{{fixture.teamHomeScore}}</span>
        <span>{{fixture.kickoffTimeFormatted}}</span>
        <span>{{fixture.teamAwayScore}}</span>
        <span class="badge">{{fixture.teamAway}}</span>
      </li>
    </ul>
  `
})
export class FixturesComponent implements OnInit {
  fixtures: Fixture[];

  constructor(private fixtureService: FixtureService) {
  }

  ngOnInit(): void {
    this.fixtureService.getFixtures()
      .then(fixtures => this.fixtures = fixtures)
  }
}
