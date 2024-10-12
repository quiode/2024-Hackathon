import { Component } from '@angular/core';
import { Game } from '../../../game';

@Component({
  selector: 'app-lecture-card',
  standalone: true,
  imports: [],
  templateUrl: './lecture-card.component.html',
  styleUrl: './lecture-card.component.css'
})
export class LectureCardComponent {

// TODO: actually get data from backend
   currentLectureGame: Game = {
    timeFrame : "",
    endDate: new Date(2024, 11, 1, 16, 0), // imterpolate from timeframe
    id: 11,
    professors: "Ueli Maurer",
    startDate: new Date(2024, 11, 1, 14, 15), // imterpolate from timeframe
    subjectTitle: "Diskrete Mathematik",
    isUpcoming : isUpcoming(new Date()) // (supposed to be same as startDate)
  }
  protected readonly isUpcoming = isUpcoming;
}

function isUpcoming(date: Date) : boolean {
  const today : Date = new Date();

  const output = today.getFullYear() === date.getFullYear() &&
    today.getMonth() === date.getMonth() &&
    today.getDate() === date.getDate();
  return true; //return output
}
