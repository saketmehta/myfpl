import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { UserService } from './user.service';

@Injectable()
export class BootstrapService {
  private fplUrl = '/rest/fpl';

  constructor(private http: Http, private userService: UserService) { }

  bootstrap(): Observable<Response[]> {
    const bootstrapStaticUrl = `${this.fplUrl}/bootstrap/static`;
    const bootstrapDynamicUrl = `${this.fplUrl}/bootstrap/dynamic`;
    return Observable.forkJoin(
      this.bootstrapDynamic(),
      this.bootstrapStatic()
    );
  }

  bootstrapStatic(): Observable<Response> {
    const bootstrapStaticUrl = `${this.fplUrl}/bootstrap/static`;
    return this.http.get(bootstrapStaticUrl)
      .map(res => res.json())
      .do(data => {
        localStorage.setItem('elements', JSON.stringify(data.elements));
        localStorage.setItem('teams', JSON.stringify(data.teams));
        localStorage.setItem('elementTypes', JSON.stringify(data.element_types));
      }, err => {
        console.error('Failed to save bootstrap static data! :(');
      }, () => {
        console.log('Saved bootstrap static data! :)');
      });
  }

  bootstrapDynamic(): Observable<Response> {
    const bootstrapDynamicUrl = `${this.fplUrl}/bootstrap/dynamic`;
    return this.http.get(bootstrapDynamicUrl)
      .map(res => res.json())
      .do(data => {
        localStorage.setItem('currentEvent', data['current-event']);
        localStorage.setItem('nextEvent', data['next-event']);
        localStorage.setItem('nextEventFixtures', JSON.stringify(data['next_event_fixtures']));
      }, err => {
        console.error('Failed to save bootstrap dynamic data! :(');
      }, () => {
        console.log('Saved bootstrap dynamic data! :)');
      });
  }

}
