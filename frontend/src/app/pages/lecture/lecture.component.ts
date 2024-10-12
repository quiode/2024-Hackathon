import { Component, computed, OnInit, Signal, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { LectureService } from '../../shared/services/lecture.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CardsViewComponent } from "./cards-view/cards-view.component";
import { Card } from '../../shared/models/Card';
import { CardAddDto, CardService } from '../../shared/services/card.service';
import { Lecture } from '../../shared/models/Lecture';

@Component({
  selector: 'app-lecture',
  standalone: true,
  imports: [CardsViewComponent],
  templateUrl: './lecture.component.html',
  styleUrl: './lecture.component.css'
})
export class LectureComponent {
  cards: Signal<Card[]>;
  lecture: Signal<Lecture | undefined>;

  constructor(private lectureService: LectureService, private route: ActivatedRoute, private router: Router, private cardService: CardService) {
    if (route.snapshot.firstChild == null) {
      router.navigate([""]);
      this.lecture = signal(undefined);
    } else {
      let id = route.snapshot.firstChild.params['id'];
      this.lecture = toSignal(this.lectureService.getLecture(id));
    }

    this.cards = computed(() => {
      const lecture = this.lecture()

      if (lecture) {
        return this.cardService.getCardsByLecture(lecture)();
      } else {
        return [];
      }
    });
  }

  onCardAdd(card: CardAddDto) {
    this.cardService.addCard(card).subscribe(_ => alert('card created'));
  }
}
