import { Component, computed, effect, OnInit, Signal, signal } from '@angular/core';
import { LectureService } from '../../shared/services/lecture.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CardsViewComponent } from "./cards-view/cards-view.component";
import { Card } from '../../shared/models/Card';
import { CardAddDto, CardService } from '../../shared/services/card.service';
import { Lecture } from '../../shared/models/Lecture';
import { Game } from '../../shared/models/Game';
import { GameService } from '../../shared/services/game.service';

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
  lectureGame = signal<Game | undefined>(undefined);

  constructor(private lectureService: LectureService, private route: ActivatedRoute, private router: Router, private cardService: CardService, private gameService: GameService) {
    if (route.snapshot.firstChild == null) {
      router.navigate([""]);
      this.lecture = signal(undefined);
    } else {
      let id = route.snapshot.firstChild.params['id'];
      this.lecture = lectureService.getLecture(id);
      gameService.getGameByLecture(id).subscribe({
        next: (v) => this.lectureGame.set(v),
        error: (e: Error) => console.error(e),
      });
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
