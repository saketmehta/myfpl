import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { AlertService } from './../alert.service';
import { UserService } from './../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userModel = {
    fullName: <string>null,
    email: <string>null,
    password: <string>null,
    passwordAgain: <string>null
  };

  constructor(private userService: UserService, private alertService: AlertService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.register(this.userModel)
      .subscribe(user => {
        this.alertService.success('Yay! You can now login.', true);
        this.router.navigate(['/home']);
      });
  }
}
