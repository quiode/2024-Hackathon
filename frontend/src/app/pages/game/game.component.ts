import { Component, signal, Signal } from '@angular/core';
import { GameService } from '../../shared/services/game.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router } from '@angular/router';
import { BoardComponent } from '../../components/board/board.component';
import { Game } from '../../shared/models/Game';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [BoardComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  game: Signal<Game | undefined> = signal(undefined);

  constructor(private gameService: GameService, private route: ActivatedRoute, private router: Router) {
    if (route.snapshot.firstChild == null) {
      router.navigate([""]);
    }
    else {
      let id = route.snapshot.firstChild.params['id'];
      this.game = toSignal(this.gameService.getGame(id));
    }
  }

}
