import { Component, computed, Input, input, signal, Signal } from '@angular/core';
import { Game } from '../../../shared/models/Game';
import { Lecture } from '../../../shared/models/Lecture';
import { LectureTimeframe } from '../../../shared/models/LectureTimeframe';
import { CommonModule } from '@angular/common';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lecture-card',
  standalone: true,
  imports: [CommonModule, FontAwesomeModule],
  templateUrl: './lecture-card.component.html',
})

export class LectureCardComponent {
  lecture = input.required<Lecture>();
  upcomingSection = input.required<boolean>();
  start = "";
  end = "";
  upcoming: Signal<boolean>;
  next: Signal<Date[] | null[]>;

  constructor(private router: Router) {
    this.router = router;
    this.next = computed(() => this.getNext(this.lecture()));
    this.upcoming = computed(() => this.isUpcoming(this.lecture()));
  }

  getNext(lecture: Lecture): Date[] | null[] {
    const today = new Date();
    for (let d of lecture.dates) {
      if (new Date(d.endDate) > today) {
        return [new Date(d.startDate), new Date(d.endDate)];
      }
    }
    return [null, null];
  }

  isUpcoming(lecture: Lecture): boolean {
    const today = new Date();
    for (let d of lecture.dates) {
      const start = new Date(d.startDate);
      if (
        start.getFullYear() === today.getFullYear() &&
        start.getMonth() === today.getMonth() &&
        start.getDate() === today.getDate()
      ) return true;
    }
    return true;
  }

  redirectToLecture() {
    this.router.navigate(["lecture", this.lecture().id])
  }

  joinGame() {
    // TODO
  }

  protected readonly faArrowRight = faArrowRight;

  // TODO: actually get data from backend
  // currentLectureGame: Game = {
  //   id: 11,
  //   lecture: {
  //     dates: [],
  //     id: 1,
  //     name: '',
  //     professors: []
  //   },
  //   professor: {
  //     id: 1,
  //     name: ''
  //   },
  //   timeframe: {
  //     endDate: 1,
  //     id: 1,
  //     startDate: 1
  //   }
  // }
}


