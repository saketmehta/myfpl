import { Component, OnInit } from '@angular/core';

import { BootstrapService } from './core/bootstrap.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  spinner = false;

  constructor(private bootstrapService: BootstrapService) { }

  ngOnInit() {
    // this.bootstrapService.bootstrap().then(() => this.setSpinner(false));
  }

  setSpinner(value: boolean) {
    this.spinner = value;
  }
}
