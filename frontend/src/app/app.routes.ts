import { Routes } from '@angular/router';
import { authGuard } from './shared/guards/auth.guard';
import { negateAuthGuard } from './shared/guards/negate-auth.guard';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LectureComponent } from './pages/lecture/lecture.component';
import { RegisterComponent } from './pages/register/register.component';

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
