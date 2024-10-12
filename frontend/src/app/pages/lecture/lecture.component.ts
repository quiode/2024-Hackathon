import { Component, computed, OnInit, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { LectureService } from '../../shared/services/lecture.service';
import { ActivatedRoute, Router } from '@angular/router';
import { faSignLanguage } from '@fortawesome/free-solid-svg-icons';
import { CardsViewComponent } from "./cards-view/cards-view.component";
import { Card } from '../../shared/models/Card';

@Component({
  selector: 'app-lecture',
  standalone: true,
  imports: [CardsViewComponent],
  templateUrl: './lecture.component.html',
  styleUrl: './lecture.component.css'
})
export class LectureComponent {
  onCardAdd(card: Card) { // TODO: dev func
    this.cards.update(cards => [...cards, card]);
  }
  cards = signal<Card[]>([{
    id: 1,
    creationDate: Date.now(),
    creator: {
      currentGame: {},
      id: 1,
      lectures: [],
      mail: "domi",
      name: "dominik schwaiger"
    },
    downvotes: [],
    upvoted: [],
    lecture: {
      dates: [],
      id: 1,
      name: 'some lecture',
      professors: []
    },
    professor: {},
    text: 'some card text'
  }]); // TODO: dev var

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
