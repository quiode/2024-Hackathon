import { Component, OnDestroy, OnInit, signal, Signal } from '@angular/core';
import { GameService } from '../../shared/services/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BoardComponent } from './board/board.component';
import { Game } from '../../shared/models/Game';
import { LectureTimeframeComponent } from "../../components/lecture-timeframe/lecture-timeframe.component";
import { interval, Subscription, switchMap, tap } from 'rxjs';
import { Bingo } from '../../shared/models/Bingo';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [BoardComponent, LectureTimeframeComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent implements OnInit, OnDestroy {
  game: Signal<Game | undefined>;
  board: Signal<Bingo | undefined>;
  updateSubscription?: Subscription;
  id: number;

  constructor(private gameService: GameService, private route: ActivatedRoute, private router: Router) {
    if (route.snapshot.firstChild == null) {
      console.log("was here")
      router.navigate([""]);
      this.game = signal(undefined);
      this.board = signal(undefined);
      this.id = 0;
    }
    else {
      this.id = route.snapshot.firstChild.params['id'];
      gameService.setGame(this.id);
      this.game = gameService.getCurrentGame();
      this.board = gameService.setBoard();
    }
  }

  ngOnInit(): void {
    this.updateSubscription = interval(5 * 1000).subscribe(_ => {
      this.gameService.setGame(this.id);
      this.gameService.setBoard();
    });
  }

  ngOnDestroy(): void {
    this.updateSubscription?.unsubscribe();
  }

}
