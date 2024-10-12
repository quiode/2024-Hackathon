import { Component } from '@angular/core';
import { GameService } from '../../shared/services/game.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  game;

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
