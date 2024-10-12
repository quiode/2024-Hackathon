import { Component, computed, Signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LectureCardComponent } from './lecture-card/lecture-card.component';
import { FaIconComponent } from "@fortawesome/angular-fontawesome";
import { faBolt, faGamepad } from "@fortawesome/free-solid-svg-icons";
import { LectureService } from '../../shared/services/lecture.service';
import { LectureTimeframe } from '../../shared/models/LectureTimeframe';
import { Lecture } from '../../shared/models/Lecture';


@Component({
  selector: 'app-dashbord',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, LectureCardComponent, FaIconComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  lectures: Signal<Lecture[]>;
  upcomingLectures: Signal<Lecture[]>;
  notUpcomingLectures: Signal<Lecture[]>;

  constructor(private lectureService: LectureService) {
    this.lectures = lectureService.getLectures();
    this.upcomingLectures = computed(() => this.lectures().filter(lecture => this.isUpcoming(lecture)));
    this.notUpcomingLectures = computed(() => this.lectures().filter(lecture => !this.isUpcoming(lecture)));
  }

  protected readonly faBolt = faBolt;
  protected readonly faGamepad = faGamepad;

  isUpcoming(lecture: Lecture): boolean {
    const now = new Date();
    for (let d of lecture.dates) {
      const start = new Date(d.startDate);
      const end = new Date(d.endDate);
      if (
        start.getFullYear() === now.getFullYear() &&
        start.getMonth() === now.getMonth() &&
        start.getDate() === now.getDate() &&
        start.getTime() <= now.getTime() && now.getTime() < end.getTime()
      ) return true;
    }
    return false;
  }
}

