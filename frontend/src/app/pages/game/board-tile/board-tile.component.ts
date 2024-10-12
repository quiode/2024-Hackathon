import { Component } from '@angular/core';
import { GameService } from '../../../shared/services/game.service';
import { Card } from '../../../shared/models/Card';

@Component({
  selector: 'app-board-tile',
  standalone: true,
  imports: [],
  templateUrl: './board-tile.component.html',
  styleUrl: './board-tile.component.css'
})
export class BoardTileComponent {
  tile: Card;

  constructor(private gameService: GameService) {
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
      upvoted: [],
      downvotes: [],
      lecture: {
        id: 1,
        name: "test",
        professors: [],
        dates: []
      },
      professor: {}

    }
  }
}
