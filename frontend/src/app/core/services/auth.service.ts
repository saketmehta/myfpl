import { Response, Http } from '@angular/http';
import { Observable, Subject, BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

  public isSignedIn: BehaviorSubject<boolean> = new BehaviorSubject(localStorage.getItem('currentUser') !== null);

  constructor(private http: Http) { }

  login(email: string, password: string): Observable<Response> {
    const url = 'rest/auth/login';
    const body = {
      email: email,
      password: password
    };
    return this.http.post(url, body)
      .do((res: Response) => {
        localStorage.setItem('currentUser', JSON.stringify(res.json()));
        this.isSignedIn.next(true);
      });
  }

  logout(): Observable<Response> {
    const url = 'rest/auth/logout';
    return this.http.post(url, {})
      .do((res: Response) => {
        localStorage.removeItem('currentUser');
        this.isSignedIn.next(false);
      });
  }

  createUser(payload: any): Observable<Response> {
    const url = 'rest/auth/create';
    return this.http.post(url, payload);
  }

}
