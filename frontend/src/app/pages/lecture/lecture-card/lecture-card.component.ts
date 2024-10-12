import { Component } from '@angular/core';
import { Game } from '../../../shared/models/Game';

@Component({
  selector: 'app-lecture-card',
  standalone: true,
  imports: [],
  templateUrl: './lecture-card.component.html',
})
export class LectureCardComponent {

// TODO: actually get data from backend
   currentLectureGame: Game = {
    id: 11,
    lectureId: 1,
    lecturer: 1,
    timeframe: {
      end: 1,
      start: 2
    }
  }
  protected readonly isUpcoming = isUpcoming;
}

function isUpcoming(date: Date) : boolean {
  const today : Date = new Date();

  const output = today.getFullYear() === date.getFullYear() &&
    today.getMonth() === date.getMonth() &&
    today.getDate() === date.getDate();
  return true; //return output //TODO
}
