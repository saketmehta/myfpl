import { Router } from '@angular/router';
import { AlertService } from './../core/alert/alert.service';
import { UserService } from './../core/services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

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
    this.userService.createUser(this.userModel)
      .subscribe(user => {
        this.alertService.success('Yay! You can now login.', true);
        this.router.navigate(['/home']);
      });
  }

}
