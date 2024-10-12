import { Component, computed, Input, input, signal, Signal } from '@angular/core';
import { Lecture } from '../../../shared/models/Lecture';
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
  upcoming = input.required<boolean>();
  start = "";
  end = "";
  next: Signal<Date[] | null[]>;

  constructor(private router: Router) {
    this.router = router;
    this.next = computed(() => this.getNext(this.lecture()));
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

  redirectToLecture() {
    this.router.navigate(["lecture", this.lecture().id])
  }

  joinGame() {
    // TODO
  }

  getWeekDay(day: number | undefined) {
    if (day == undefined) return '';
    return ['Sun.', 'Mon.', 'Tue.', 'Wed.', 'Thu.', 'Fri.', 'Sat.'][day];
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


