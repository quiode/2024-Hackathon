import { Component, Input } from '@angular/core';
import { Game } from '../../../shared/models/Game';
import {Lecture} from "../../../shared/models/Lecture";

@Component({
  selector: 'app-lecture-card',
  standalone: true,
  imports: [],
  templateUrl: './lecture-card.component.html',
})

export class LectureCardComponent {
  upcoming: boolean = false
  @Input()
  getUpcoming(): boolean {
    return this.upcoming;
  }
  setUpcoming(isUpcoming: boolean) {
    this.upcoming = isUpcoming;
  }

  // TODO: actually get data from backend
  currentLectureGame: Game = {
    id: 11,
    lecture: {
      dates: [],
      id: 1,
      name: '',
      professors: []
    },
    professor: {
      id: 1,
      name: ''
    },
    timeframe: {
      endDate: 1,
      id: 1,
      startDate: 1
    }
  }
  protected readonly isUpcoming = isUpcoming;
}

export function isUpcoming(date: Date): boolean {
  const today: Date = new Date();

  const output = today.getFullYear() === date.getFullYear() &&
    today.getMonth() === date.getMonth() &&
    today.getDate() === date.getDate();

  return output;
}


