import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { UserService } from './user.service';
import { AlertService } from './alert.service';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private userService: UserService, private alertService: AlertService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.userService.isLoggedIn()) {
      return true;
    }
    // not logged in
    this.alertService.error('Holy guacamole! Please log in!', true);
    this.router.navigate(['/home'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
