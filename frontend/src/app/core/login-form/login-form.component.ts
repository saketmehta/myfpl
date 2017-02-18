import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService } from '../../core/alert.service';
import { UserService } from '../../core/user.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  model = {
    email: <string>null,
    password: <string>null
  };

  constructor(private userService: UserService, private router: Router, private alertService: AlertService) {
  };

  onSubmit() {
    this.userService.login(this.model.email, this.model.password)
      .then(() => {
        this.alertService.clear();
        this.router.navigate(['home']);
      })
      .catch(() => {
        this.router.navigate(['home'], { queryParams: { error: 1 } });
      });
  }
}
