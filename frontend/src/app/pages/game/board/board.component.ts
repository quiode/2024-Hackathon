import { Component, Signal } from '@angular/core';
import { BoardTileComponent } from "../board-tile/board-tile.component";
import { GameService } from '../../../shared/services/game.service';
import { Bingo } from '../../../shared/models/Bingo';

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [BoardTileComponent],
  templateUrl: './board.component.html',
  styleUrl: './board.component.css'
})
export class BoardComponent {
  board: Signal<Bingo | undefined>;
  constructor(private gameService: GameService) {
    this.board = this.gameService.setBoard();
  }
}
