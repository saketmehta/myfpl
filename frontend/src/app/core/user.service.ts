import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';

import { Router } from '@angular/router';
import { User } from './models/user';
import { AlertService } from './alert.service';
import { LoggerService } from './logger.service';

@Injectable()
export class UserService {
  private authUrl = '/rest/auth';
  private userUrl = '/rest/user';

  constructor(private logger: LoggerService, private http: Http, private router: Router, private alertService: AlertService) { }

  login(username: string, password: string): Promise<void> {
    const url = `${this.authUrl}/login`;
    return this.http.post(url, { email: username, password: password })
      .toPromise()
      .then(response => {
        let res = response.json();
        if (res) {
          localStorage.setItem('currentUser', JSON.stringify(res));
        }
      })
      .catch(this.handleError.bind(this));
  }

  logout(): Promise<void> {
    const url = `${this.authUrl}/logout`;
    return this.http.post(url, {})
      .toPromise()
      .then(response => {
        localStorage.removeItem('currentUser');
      })
      .catch(this.handleError.bind(this));
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem('currentUser')) {
      return true;
    } else {
      return false;
    }
  }

  getCurrentUser(): User {
    return <User>JSON.parse(localStorage.getItem('currentUser'));
  }

  updateUser(payload: any) {
    const url = `${this.userUrl}/update`;
    return this.http.post(url, payload)
      .map(this.extractData)
      .catch(this.handleError.bind(this));
  }

  updatePassword(payload: any): Observable<User> {
    const url = `${this.userUrl}/change-password`;
    return this.http.post(url, payload)
      .map(this.extractData)
      .catch(this.handleError.bind(this));
  }

  register(payload: any): Observable<User> {
    const url = `${this.authUrl}/create`;
    return this.http.post(url, payload)
      .map(this.extractData)
      .catch(this.handleError.bind(this));
  }

  private extractData(res: Response) {
    let data = res.text() || '{}';
    return JSON.parse(data) || {};
  }

  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      if (error.status === 401) {
        this.logout().then(() => {
          this.alertService.error('Uh oh! Please log in!', true);
          this.router.navigate(['/home']);
        });
      }
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    this.logger.error(errMsg);
    return Observable.throw(errMsg);
  }

}
