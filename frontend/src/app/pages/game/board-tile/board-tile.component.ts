import { Component, input, Input, Signal } from '@angular/core';
import { GameService } from '../../../shared/services/game.service';
import { Card } from '../../../shared/models/Card';
import { CommonModule } from '@angular/common';
import { Bingo } from '../../../shared/models/Bingo';

@Component({
  selector: 'app-board-tile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './board-tile.component.html',
  styleUrl: './board-tile.component.css'
})
export class BoardTileComponent  {
  @Input() status: number;
  card = input.required<Card>();
  pos = input.required<number>();

  board: Signal<Bingo | undefined>;

  constructor(private gameService: GameService) {
    this.status = 0;
    this.board = gameService.setBoard();
  }

  clickCard() {
    this.gameService.clickCard(this.pos());
  }
}
