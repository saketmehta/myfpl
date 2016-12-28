import {Component} from "@angular/core";
import {FixtureService} from "./fixtures/fixture.service";
import {Fixture} from "./fixtures/fixture";

@Component({
  selector: 'my-app',
  template: `
    <nav>
      <a routerLink="/" routerLinkActive="active">Home</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  fixtures: Fixture[];

  constructor(private fixtureService: FixtureService) {
  }

  ngOnInit(): void {
    this.fixtureService.getFixtures()
      .then(fixtures => this.fixtures = fixtures)
  }
}
