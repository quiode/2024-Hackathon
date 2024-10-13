import { Component, computed, Input, input, signal, Signal } from '@angular/core';
import { Lecture } from '../../../shared/models/Lecture';
import { CommonModule } from '@angular/common';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Router } from '@angular/router';
import { LectureTimeframeComponent } from "../../../components/lecture-timeframe/lecture-timeframe.component";
import { LectureTimeframe } from '../../../shared/models/LectureTimeframe';
import { GameService } from '../../../shared/services/game.service';

@Component({
  selector: 'app-lecture-card',
  standalone: true,
  imports: [CommonModule, FontAwesomeModule, LectureTimeframeComponent, LectureCardComponent],
  templateUrl: './lecture-card.component.html',
})

export class LectureCardComponent {
  lecture = input.required<Lecture>();
  upcoming = input.required<boolean>();
  start = "";
  end = "";
  next: Signal<LectureTimeframe | undefined>;

  constructor(private router: Router, private gameService: GameService) {
    this.router = router;
    this.next = computed(() => this.getNext(this.lecture()));
  }

  getNext(lecture: Lecture): LectureTimeframe | undefined {
    const today = new Date();
    for (let d of lecture.dates) {
      if (new Date(d.endDate) > today) {
        return d;
      }
    }
    return undefined;
  }

  redirectToLecture() {
    this.router.navigate(["lecture", this.lecture().id])
  }

  joinGame() {
    this.gameService.getGameByLecture(this.lecture().id).subscribe({
      next: (v) => {
        if (v != null) {
          this.gameService.joinGame(v.id);
          this.router.navigate(["game", v.id])
        }
        else {
          this.gameService.createGame(this.lecture().id).subscribe({
            next: (v) => {
              this.gameService.joinGame(v)
              this.router.navigate(["game", v])
            },
            error: (e) => console.error(e)
          });
        }
      },
      error: (e) => console.error(e)
    });
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


