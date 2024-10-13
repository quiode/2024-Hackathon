import { Component, signal, Signal } from '@angular/core';
import { GameService } from '../../shared/services/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BoardComponent } from './board/board.component';
import { Game } from '../../shared/models/Game';
import { LectureTimeframeComponent } from "../../components/lecture-timeframe/lecture-timeframe.component";

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [BoardComponent, LectureTimeframeComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  game: Signal<Game | undefined>;

  constructor(private gameService: GameService, private route: ActivatedRoute, private router: Router) {
    if (route.snapshot.firstChild == null) {
      console.log("was here")
      router.navigate([""]);
      this.game = signal(undefined);
    }
    else {
      let id = route.snapshot.firstChild.params['id'];
      gameService.setGame(id);
      this.game = gameService.getCurrentGame();
    }
  }

}
