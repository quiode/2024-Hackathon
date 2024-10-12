import { Component } from '@angular/core';

@Component({
  selector: 'app-lecture-card',
  standalone: true,
  imports: [],
  templateUrl: './lecture-card.component.html',
  styleUrl: './lecture-card.component.css'
})
export class LectureCardComponent {
  today : Date = new Date();

  lectureGame  = {
    endDate: new Date(2024, 11, 1, 16, 0),
    id: 11,
    professor: "Ueli Maurer",
    startDate: new Date(2024, 11, 1, 14, 15),
    subjectTitle: "Diskrete Mathematik",
    isUpcoming: true /* this.today.getFullYear() === new Date(2024, 11, 1, 16, 0).getFullYear() && //blueprint for upcoming lecture display logic
      this.today.getMonth() === new Date(2024, 11, 1, 16, 0).getMonth() && // new date (...) is supposed to be replaced by data from the backend
      this.today.getDate() === new Date(2024, 11, 1, 16, 0).getDate() */
  }
}
