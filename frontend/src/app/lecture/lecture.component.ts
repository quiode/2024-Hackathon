import { Component, computed, OnInit, signal } from '@angular/core';
import { LectureService } from '../shared/services/lecture.service';
import { Lecture } from '../shared/models/Lecture';
import { Observable } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-lecture',
  standalone: true,
  imports: [],
  templateUrl: './lecture.component.html',
  styleUrl: './lecture.component.css'
})
export class LectureComponent {
  lecture;
  // lectureData;
  
  constructor(private lectureService: LectureService) {
    this.lecture = toSignal(this.lectureService.getLecture());
    // this.lectureData = computed(() => this.lecture()?.lecturers);
    }

}
