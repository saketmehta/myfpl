import { AuthGuardService } from './../core/services/auth-guard.service';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MyTeamComponent } from './my-team.component';

const routes: Routes = [
  { path: 'my-team', component: MyTeamComponent, canActivate: [AuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class MyTeamRoutingModule { }
