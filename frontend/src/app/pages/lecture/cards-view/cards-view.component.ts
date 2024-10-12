import { Component, computed, input, output } from '@angular/core';
import { Card } from '../../../shared/models/Card';
import { Lecture } from '../../../shared/models/Lecture';
import { faChevronDown, faChevronUp } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { UserService } from '../../../shared/services/user.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { CommonModule } from '@angular/common';
import { CardAddDto } from '../../../shared/services/card.service';

@Component({
  selector: 'app-cards-view',
  standalone: true,
  imports: [FontAwesomeModule, CommonModule],
  templateUrl: './cards-view.component.html',
  styleUrl: './cards-view.component.css'
})
export class CardsViewComponent {
  user;
  constructor(private userService: UserService) {
    this.user = toSignal(userService.getUser());;
  }

  // Font Awesome
  up = faChevronUp;
  down = faChevronDown;

  // IO
  cards = input.required<Card[]>();
  lecture = input.required<Lecture>();
  cardAdd = output<CardAddDto>();

  // Computed
  professors = computed(() => this.lecture().professors);
  sortedCards = computed(() => [...this.cards().sort((a, b) => a.creationDate - b.creationDate)])

  onCardAdd() {
    throw new Error('Method not implemented.');
  }

  onDownvote(card: Card) {
    throw new Error('Method not implemented.');
  }

  onUpvote(card: Card) {
    throw new Error('Method not implemented.');
  }

  hasUpvoted(card: Card) {
    return card.upvotes.some(val => this.user()?.id == val.id);
  }

  hasDownvoted(card: Card) {
    return card.downvotes.some(val => this.user()?.id == val.id);
  }
}
