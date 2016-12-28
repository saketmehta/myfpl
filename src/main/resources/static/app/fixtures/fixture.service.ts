import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {Fixture} from "./fixture";
@Injectable()
export class FixtureService {
  private fixturesUrl = 'fixtures/current';

  constructor(private http: Http) {
  }

  getFixtures(): Promise<Fixture[]> {
    return this.http.get(this.fixturesUrl)
      .toPromise()
      .then(response => response.json() as Fixture[])
  }
}
