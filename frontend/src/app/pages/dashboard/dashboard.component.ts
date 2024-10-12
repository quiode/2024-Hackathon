import { Component, OnInit, signal, Signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LectureCardComponent } from '../lecture/lecture-card/lecture-card.component';
import { FaIconComponent } from "@fortawesome/angular-fontawesome";
import { faBolt } from "@fortawesome/free-solid-svg-icons";
import { LectureService } from '../../shared/services/lecture.service';
import { Lecture } from '../../shared/models/Lecture';

@Component({
  selector: 'app-dashbord',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, LectureCardComponent, FaIconComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})



export class DashboardComponent {
  lectures;

  constructor(private lectureService: LectureService) {
    this.lectures = lectureService.getLectures();
  }

  protected readonly faBolt = faBolt;
  protected readonly getUpcoming = getUpcoming;
  protected readonly getLectures = getLectures;
}

function getUpcoming(): Lecture[] {
  return getLectures().filter((lecture: Lecture) => (isUpcoming(lecture.dates)));
}

function getLectures(): Lecture[] {
  return [];
}

function isUpcoming(dates: LectureTimeframe[]) {
  const today = new Date();
  for (let d of dates) {
    const start = new Date(Date.UTC(d.startDate));
    if (
      start.getFullYear() === today.getFullYear() &&
      start.getMonth() === today.getMonth() &&
      start.getDate() === today.getDate()
    ) return true;
  }
  return false;
}
