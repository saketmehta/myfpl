import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {MaterialModule} from "@angular/material";
import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";
import {FixturesComponent} from "./fixtures/fixtures.component";
import {HomeComponent} from "./home.component";
import {LeaguesComponent} from "./leagues/leagues.component";
import {TeamService} from "./teams/team.service";
import {FixtureService} from "./fixtures/fixture.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    FixturesComponent,
    HomeComponent,
    LeaguesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialModule.forRoot(),
    AppRoutingModule
  ],
  providers: [
    TeamService,
    FixtureService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
