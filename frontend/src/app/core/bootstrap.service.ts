import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { LoggerService } from './logger.service';
import { UserService } from './user.service';

@Injectable()
export class BootstrapService {
  private fplUrl = '/rest/fpl';

  constructor(private http: Http, private logger: LoggerService, private userService: UserService) { }

  bootstrap(): Promise<void> {
    return this.bootstrapStatic().then(() => this.bootstrapDynamic());
  }

  bootstrapStatic(): Promise<void> {
    const bootstrapStaticUrl = `${this.fplUrl}/bootstrap/static`;
    return this.http.get(bootstrapStaticUrl)
      .toPromise()
      .then(response => {
        let data = response.json();
        localStorage.setItem('elements', JSON.stringify(data.elements));
        localStorage.setItem('teams', JSON.stringify(data.teams));
        localStorage.setItem('elementTypes', JSON.stringify(data.element_types));
        this.logger.log('Saved bootstrap static data! :)');
      })
      .catch((error) => {
        this.logger.error('Failed to save bootstrap static data! :(');
      });
  }

  bootstrapDynamic(): Promise<void> {
    const bootstrapDynamicUrl = `${this.fplUrl}/bootstrap/dynamic`;
    return this.http.get(bootstrapDynamicUrl)
      .toPromise()
      .then(response => {
        let data = response.json();
        localStorage.setItem('currentEvent', data['current-event']);
        localStorage.setItem('nextEvent', data['next-event']);
        localStorage.setItem('nextEventFixtures', JSON.stringify(data['next_event_fixtures']));
        this.logger.log('Saved bootstrap dynamic data! :)');
      })
      .catch((error) => {
        this.logger.error('Failed to save bootstrap dynamic data! :(');
      });
  }
}
