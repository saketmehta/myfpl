import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { SignupComponent } from './signup.component';

const routes: Routes = [
  { path: 'signup', component: SignupComponent },
];

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
  ],
  declarations: [SignupComponent]
})
export class SignupModule { }
