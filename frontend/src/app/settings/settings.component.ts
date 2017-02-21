import { AlertService } from './../core/alert/alert.service';
import { UserService } from './../core/services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../core/models/user';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  updateUserModel = {
    userId: <number>null,
    fullName: <string>null,
    email: <string>null
  };
  changePasswordModel = {
    oldPassword: <string>null,
    newPassword: <string>null,
    newPasswordAgain: <string>null
  };

  constructor(private userService: UserService, private alertService: AlertService) { }

  ngOnInit() {
    let user = <User>JSON.parse(localStorage.getItem('currentUser'));
    this.updateUserModel.userId = user.userId;
    this.updateUserModel.fullName = user.fullName;
    this.updateUserModel.email = user.email;
  }

  updateProfile() {
    const payload = {
      userId: this.updateUserModel.userId,
      email: this.updateUserModel.email,
      fullName: this.updateUserModel.fullName
    };
    this.userService.updateUser(payload)
      .subscribe(user => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.alertService.success('Saved!');
      });
  }

  updatePassword() {
    const payload = {
      userId: this.updateUserModel.userId,
      oldPassword: this.changePasswordModel.oldPassword,
      newPassword: this.changePasswordModel.newPassword
    };
    this.userService.updatePassword(payload)
      .subscribe(res => {
        this.changePasswordModel = {
          oldPassword: <string>null,
          newPassword: <string>null,
          newPasswordAgain: <string>null
        };
        this.alertService.success('Password changed!');
      });
  }

}
