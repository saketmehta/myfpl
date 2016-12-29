import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Team} from "./team";
import {Observable} from "rxjs";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/map";

@Injectable()
export class TeamService {
  private teamsUrl = 'rest/teams';

  constructor(private http: Http) {
  }

  getTeams(): Observable<Team[]> {
    const url = `${this.teamsUrl}/all`;
    return this.http.get(url)
      .map(response => <Team[]>response.json());
  }
}
