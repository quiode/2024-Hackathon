import { Component } from '@angular/core';
import { Lecture } from '../interfaces/lecture';

@Component({
  selector: 'app-lecture',
  standalone: true,
  imports: [],
  templateUrl: './lecture.component.html',
  styleUrl: './lecture.component.css'
})
export class LectureComponent {
  
  lecture: Lecture = {
    title: null,
    lecturers: null,
    times: null,
    cards: null
  }

  getLiveGame() {
    return false;
  }
  getLecturers() {
    return this.lecture.lecturers;
  }
  getTitle() {
    return this.lecture.title;
  }

}
