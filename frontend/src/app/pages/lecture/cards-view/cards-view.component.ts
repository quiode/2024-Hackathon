import { Component, computed, input, output, signal, Signal } from '@angular/core';
import { Card } from '../../../shared/models/Card';
import { Lecture } from '../../../shared/models/Lecture';
import { faChevronDown, faChevronUp } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { UserService } from '../../../shared/services/user.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { CommonModule } from '@angular/common';
import { CardAddDto, CardService } from '../../../shared/services/card.service';
import { User } from '../../../shared/models/User';
import { Professor } from '../../../shared/models/Professor';
import { animate, style, transition, trigger } from '@angular/animations';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-cards-view',
  standalone: true,
  imports: [FontAwesomeModule, CommonModule, ReactiveFormsModule],
  templateUrl: './cards-view.component.html',
  styleUrl: './cards-view.component.css',
  animations: [
    trigger('formTrigger', [
      transition(':enter', [style({ opacity: 0 }), animate('100ms', style({ opacity: 1 }))]),
      transition(':leave', [animate('100ms', style({ opacity: 0 }))]),
    ]),
  ]
})
export class CardsViewComponent {
  // Signals
  user: Signal<User | undefined>;
  wantToEdit = signal(false);

  // From
  cardFrom = new FormGroup({
    professor: new FormControl<Professor | null>(null, [Validators.required]),
    text: new FormControl<string>('', [Validators.required, Validators.minLength(2), Validators.maxLength(100)])
  });

  // Font Awesome
  up = faChevronUp;
  down = faChevronDown;

  // IO
  cards = input.required<Card[]>();
  lecture = input.required<Lecture>();
  cardAdd = output<CardAddDto>();

  // Computed
  professors = computed(() => this.lecture().professors);
  professorCardMap: Signal<Map<Professor, Card[]>> = computed(() => {
    const cards = this.cards();
    return new Map(this.professors().map(prof => { return [prof, cards.filter(card => card.professor.id == prof.id).sort((a, b) => new Date(a.creationDate).getTime() - new Date(b.creationDate).getTime())] }));
  });
  hasCards: Signal<boolean> = computed(() => [...this.professorCardMap().values()].some(arr => arr.length > 0));

  constructor(private userService: UserService, private cardService: CardService) {
    this.user = toSignal(userService.getUser());;
  }

  toggleCardForm() {
    this.wantToEdit.update(edit => !edit);
  }

  onDownvote(card: Card) {
    this.cardService.downvote(card).subscribe(_ => this.cardService.fetchCardsByLecture(this.lecture()));
  }

  onUpvote(card: Card) {
    this.cardService.upvote(card).subscribe(_ => this.cardService.fetchCardsByLecture(this.lecture()));
  }

  hasUpvoted(card: Card) {
    return card.upvotes.some(val => this.user()?.id == val.id);
  }

  hasDownvoted(card: Card) {
    return card.downvotes.some(val => this.user()?.id == val.id);
  }

  onSubmitCard() {
    if (this.cardFrom.valid) {
      const value = this.cardFrom.value;

      this.wantToEdit.set(false);
      this.hasDownvoted
      this.cardService.addCard({
        lecture: this.lecture(),
        prof: value.professor!,
        text: value.text!
      }).subscribe(_ => this.cardFrom.reset());
    }
  }
}
