import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../models/user';
import { UserService } from '../user.service';

@Component({
  moduleId: module.id,
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private userService: UserService, private router: Router) { }

  isLoggedIn(): boolean {
    return this.userService.isLoggedIn();
  }

  getCurrentUser(): User {
    return this.userService.getCurrentUser();
  }

  logout() {
    this.userService.logout().then(() => {
      this.router.navigate(['/home']);
    });
  }
}
