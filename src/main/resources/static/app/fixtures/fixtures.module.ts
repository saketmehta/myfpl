import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {FixturesComponent} from "./fixtures.component";
import {FixtureService} from "./fixture.service";
import {CommonModule} from "@angular/common";
@NgModule({
  imports: [
    CommonModule,
    HttpModule,
    FormsModule
  ],
  declarations: [
    FixturesComponent
  ],
  providers: [
    FixtureService
  ],
  exports: [
    FixturesComponent
  ]
})
export class FixturesModule {
}
