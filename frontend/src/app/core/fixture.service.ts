import { Observable } from 'rxjs';
import { Http } from '@angular/http';
import { Injectable } from '@angular/core';

import { Fixture } from './models/fpl';

@Injectable()
export class FixtureService {
  private fplUrl = '/rest/fpl';

  constructor(private http: Http) { }

  getCurrentEventNumber(): number {
    return <number>JSON.parse(localStorage.getItem('currentEvent'));
  }

  getNextEvent(): Fixture[] {
    return <Fixture[]>JSON.parse(localStorage.getItem('nextEventFixtures'));
  }

  getEvent(eventNumber: number): Observable<Fixture[]> {
    const url = `${this.fplUrl}/fixtures/?event=${eventNumber}`;
    return this.http.get(url)
      .map(res => res.json() as Fixture[]);
  }

}
