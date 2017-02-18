import { Http } from '@angular/http';
import { Injectable } from '@angular/core';
import { Team } from './models/fpl';

@Injectable()
export class TeamService {

  constructor(private http: Http) { }

  getTeams(): Team[] {
    return <Team[]>JSON.parse(localStorage.getItem('teams'));
  }

}
