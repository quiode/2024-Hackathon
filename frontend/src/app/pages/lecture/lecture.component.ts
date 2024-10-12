import { Component, computed, OnInit, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { LectureService } from '../../shared/services/lecture.service';
import { ActivatedRoute, Router } from '@angular/router';
import { faSignLanguage } from '@fortawesome/free-solid-svg-icons';
import { NotFoundComponent } from "../../components/not-found/not-found.component";

@Component({
  selector: 'app-lecture',
  standalone: true,
  imports: [NotFoundComponent],
  templateUrl: './lecture.component.html',
  styleUrl: './lecture.component.css'
})
export class LectureComponent {
  lecture;
  // lectureData;
  
  constructor(private lectureService: LectureService, private route: ActivatedRoute, private router: Router) {
    if (route.snapshot.firstChild == null) {
      router.navigate([""]);
      this.lecture = signal(undefined);
    } else {
      let id = route.snapshot.firstChild.params['id'];
      this.lecture = toSignal(this.lectureService.getLecture(id));
    }
    // this.lectureData = computed(() => this.lecture()?.lecturers);
  }

}
