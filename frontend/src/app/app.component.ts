import { BootstrapService } from './core/services/bootstrap.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  spinner = false;

  constructor(private bootstrapService: BootstrapService) { }

  ngOnInit() {
    this.bootstrapService.bootstrap().subscribe(() => this.spinner = false);
  }

  setSpinner(value: boolean) {
    this.spinner = value;
  }
}
