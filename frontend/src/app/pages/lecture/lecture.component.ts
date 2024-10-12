import { Component, computed, OnInit, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { LectureService } from '../../shared/services/lecture.service';

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
