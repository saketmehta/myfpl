import { League } from './../models/league';
import { AlertService } from './../alert/alert.service';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LeagueService {
  private leagueUrl = '/rest/leagues';

  // tslint:disable-next-line:max-line-length
  constructor(private http: Http, private alertService: AlertService, private router: Router, private userService: UserService) { }

  fetch(): Observable<League[]> {
    const url = `${this.leagueUrl}/fetch`;
    return this.http.get(url)
      .map(this.extractData);
  }

  create(payload: any): Observable<League> {
    const url = `${this.leagueUrl}/create`;
    return this.http.post(url, payload)
      .map(this.extractData);
  }

  private extractData(res: Response) {
    let data = res.text() || '{}';
    return JSON.parse(data) || {};
  }
}
