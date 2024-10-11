import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LectureComponent } from './lecture/lecture.component';

export const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    title: 'Dashboard'
  },
  {
    path: 'lecture',
    component: LectureComponent
  }
];
