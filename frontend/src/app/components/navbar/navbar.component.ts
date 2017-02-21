import { BehaviorSubject } from 'rxjs';
import { AlertService } from './../../core/alert/alert.service';
import { AuthService } from './../../core/services/auth.service';
import { User } from './../../core/models/user';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isSignedIn: boolean;

  constructor(private authService: AuthService, private router: Router, private alertService: AlertService) { }

  ngOnInit() {
    this.authService.isSignedIn.subscribe(value => {
      this.isSignedIn = value;
    });
  }

  login(email: string, password: string) {
    this.authService.login(email, password)
      .subscribe(() => {
        this.alertService.clear();
        this.router.navigate(['home']);
      }, e => this.handleError(e));
  }

  logout() {
    this.authService.logout()
      .subscribe(res => {
        console.log('here2424');
        this.router.navigate(['/home']);
      }, e => this.handleError(e));
  }

  private handleError(error) {
    console.log('error');
    switch (error.status) {
      case 401:
        this.alertService.error('Email or password is wrong.');
    }
  }

}
