import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Fixture} from "./fixture";
import {Observable} from "rxjs";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/map";

@Injectable()
export class FixtureService {
  private fixturesUrl = 'rest/fixtures';

  constructor(private http: Http) {
  }

  getCurrentFixtures(): Observable<Fixture[]> {
    const url = `${this.fixturesUrl}/current'`;
    return this.http.get(url)
      .map(response => <Fixture[]>response.json());
  }

  getFixture(fixtureId: number): Promise<Fixture> {
    const url = `${this.fixturesUrl}/${fixtureId}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Fixture);
  }
}
