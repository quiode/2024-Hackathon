import { Component, input, output } from '@angular/core';
import { Card } from '../../../shared/models/Card';
import { Lecture } from '../../../shared/models/Lecture';

@Component({
  selector: 'app-cards-view',
  standalone: true,
  imports: [],
  templateUrl: './cards-view.component.html',
  styleUrl: './cards-view.component.css'
})
export class CardsViewComponent {
  cards = input.required<Card[]>();
  cardAdd = output<Card>();
}
