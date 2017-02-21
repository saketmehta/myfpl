import { AlertService } from './../alert/alert.service';
import { User } from './../models/user';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class UserService {

  private userUrl = '/rest/user';

  constructor(private http: Http, private router: Router, private alertService: AlertService) { }

  fetchUser(id: number): Observable<User> {
    const url = `${this.userUrl}/fetch`;
    return this.http.get(url).map(res => res.json());
  }

  updateUser(payload: any): Observable<Response> {
    const url = `${this.userUrl}/update`;
    return this.http.post(url, payload);
  }

  updatePassword(payload: any): Observable<Response> {
    const url = `${this.userUrl}/change-password`;
    return this.http.post(url, payload);
  }

  createUser(payload: any): Observable<Response> {
    const url = `${this.userUrl}/create`;
    return this.http.post(url, payload);
  }

}
