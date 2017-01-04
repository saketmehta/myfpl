import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";
import {FixturesComponent} from "./fixtures/fixtures.component";
import {HomeComponent} from "./home/home.component";
import {LeaguesComponent} from "./leagues/leagues.component";
import {TeamService, AuthService, FixtureService} from "./_services/index";
import {AuthGuard} from "./_guards/index";

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
    AppRoutingModule
  ],
  providers: [
    AuthGuard,
    AuthService,
    TeamService,
    FixtureService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
