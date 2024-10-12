import { Component, Input } from '@angular/core';
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
  tile: Card;
  @Input() status: number;

  constructor(private gameService: GameService) {
    this.status = 0;
    this.tile = {
      id: 1,
      text: "test",
      creationDate: 1,
      creator: {
        id: 1,
        name: "test",
        mail: "test",
        lectures: [], // TODO
        currentGame: null // TODO
      },
      upvotes: [],
      downvotes: [],
      lecture: {
        id: 1,
        name: "test",
        professors: [],
        dates: []
      },
      professor: {
        id: 1,
        name: "test"
      }

    }
  }

  selectTile() {
    if (this.status < 2) {
      this.status += 1;
    }
  }
}
