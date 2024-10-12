import { Component, computed, Signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LectureCardComponent } from '../lecture/lecture-card/lecture-card.component';
import { FaIconComponent } from "@fortawesome/angular-fontawesome";
import { faBolt } from "@fortawesome/free-solid-svg-icons";
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
  upcoming: Signal<Lecture[]>;

  constructor(private lectureService: LectureService) {
    this.lectures = lectureService.getLectures();
    this.upcoming = computed(() => this.lectures().filter(lecture => this.isUpcoming(lecture.dates)));
  }

  protected readonly faBolt = faBolt;

  isUpcoming(dates: LectureTimeframe[]) {
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
}

