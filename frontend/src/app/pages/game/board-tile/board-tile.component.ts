import { Component, input, Input } from '@angular/core';
import { GameService } from '../../../shared/services/game.service';
import { Card } from '../../../shared/models/Card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-board-tile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './board-tile.component.html',
  styleUrl: './board-tile.component.css'
})
export class BoardTileComponent {
  status = input<number>(0);
  card = input.required<Card>();
  pos = input.required<number>();

  constructor(private gameService: GameService) {
  }

  clickCard() {
    this.gameService.clickCard(this.pos());
  }
}
