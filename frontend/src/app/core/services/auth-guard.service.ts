import { AuthService } from './auth.service';
import { AlertService } from './../alert/alert.service';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { UserService } from './user.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private authService: AuthService, private alertService: AlertService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return this.authService.isSignedIn.map(value => {
      if (!value) {
        this.alertService.error('Holy guacamole! Please log in!', true);
        this.router.navigate(['/home']);
      }
      return value;
    });
  }

}
