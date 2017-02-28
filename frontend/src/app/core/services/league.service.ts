import { League } from './../models/league';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class LeagueService {
  private leagueUrl = '/rest/leagues';

  constructor(private http: Http, private router: Router) { }

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

  delete(leagueId: number): Observable<boolean> {
    const url = `${this.leagueUrl}/delete`;
    return this.http.post(url, leagueId)
      .map(this.extractData);
  }

  join(inviteCode: string): Observable<League> {
    const url = `${this.leagueUrl}/join`;
    return this.http.post(url, inviteCode)
      .map(this.extractData);
  }

  leave(leagueId: number, userId: number): Observable<boolean> {
    const url = `${this.leagueUrl}/leave`;
    const payload = {
      leagueId: leagueId,
      userId: userId
    };
    return this.http.post(url, payload)
      .map(this.extractData);
  }

  private extractData(res: Response) {
    let data = res.text() || '{}';
    return JSON.parse(data) || {};
  }
}
