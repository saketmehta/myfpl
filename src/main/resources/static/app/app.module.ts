import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app-routing.module";
import {FixturesModule} from "./fixtures/fixtures.module";

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    FixturesModule
  ],
  declarations: [
    AppComponent
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
