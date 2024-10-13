import { Component, computed, input, Input, signal, Signal } from '@angular/core';
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
  // status: Signal<number>;
  isClicked = signal<boolean>(false);
  card = input.required<Card>();
  pos = input.required<number>();

  // board: Signal<Bingo | undefined>;

  constructor(private gameService: GameService) {
    // this.board = gameService.setBoard();
    // this.status = computed(() => this.board()!.ntValidated[this.pos()])
  }

  clickCard() {
    // this.gameService.clickCard(this.pos());
    this.isClicked.set(true);
  }
}
