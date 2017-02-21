import { SignupModule } from './signup/signup.module';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { HomeModule } from './home/home.module';
import { MyTeamModule } from './my-team/my-team.module';
import { LeagueModule } from './league/league.module';
import { SettingsModule } from './settings/settings.module';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,

    CoreModule,
    SharedModule,
    HomeModule,
    MyTeamModule,
    LeagueModule,
    SettingsModule,
    AppRoutingModule,
    SignupModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
