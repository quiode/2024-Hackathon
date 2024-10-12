import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LectureComponent } from './lecture/lecture.component';
import { authGuard } from './shared/guards/auth.guard';
import { RegisterComponent } from './register/register.component';
import { negateAuthGuard } from './shared/guards/negate-auth.guard';

export const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    title: 'Dashboard',
    canActivate: [authGuard]
  },
  {
    path: 'lecture',
    component: LectureComponent,
    canActivate: [authGuard]
  }, {
    path: 'register',
    component: RegisterComponent,
    canActivate: [negateAuthGuard]
  }
];
