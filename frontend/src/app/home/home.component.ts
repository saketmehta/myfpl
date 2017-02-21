import { FixtureService } from './../core/services/fixture.service';
import { TeamService } from './../core/services/team.service';
import { Fixture, Team } from './../core/models/fpl';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  eventNumber: number;
  teams: Team[];
  fixtures: Fixture[];

  constructor(private teamService: TeamService, private fixtureService: FixtureService) { }

  ngOnInit() {
    this.eventNumber = this.fixtureService.getCurrentEventNumber() + 1;
    this.teams = this.teamService.getTeams();
    this.fixtures = this.fixtureService.getNextEvent();
  }

  getTeam(teamId: number) {
    return this.teams.find(team => team.id === teamId);
  }

  previousFixtures() {
    this.fixtureService.getEvent(this.eventNumber - 1)
      .subscribe(fixtures => {
        this.fixtures = fixtures;
        this.eventNumber -= 1;
      });
  }

  nextFixtures() {
    this.fixtureService.getEvent(this.eventNumber + 1)
      .subscribe(fixtures => {
        this.fixtures = fixtures;
        this.eventNumber += 1;
      });
  }
}
