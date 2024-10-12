import { Component, Input, signal, Signal } from '@angular/core';
import { BoardTileComponent } from "../board-tile/board-tile.component";
import { GameService } from '../../shared/services/game.service';
import { Game } from '../../shared/models/Game';

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [BoardTileComponent],
  templateUrl: './board.component.html',
  styleUrl: './board.component.css'
})
export class BoardComponent {
  board;

  @Input() game: Signal<Game | undefined> = signal(undefined);
  constructor(private gameService: GameService) {
    this.board = this.gameService.getBoard();
  }
}
