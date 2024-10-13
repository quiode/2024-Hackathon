import { computed, Injectable, Signal, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { backendURL } from '../constants';
import { Game } from '../models/Game';
import { UserService } from './user.service';
import { Bingo } from '../models/Bingo';
import { Card } from '../models/Card';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private game = signal<Game | undefined>(undefined);
  private board = signal<Bingo | undefined>(undefined);

  constructor(private http: HttpClient, private userService: UserService) { }

  getGameByLecture(lectureId: number) : Observable<Game> {
    return this.http.get<Game>(backendURL() + '/game/lecture/' + lectureId + '/current');
  }

  setGameByLecture(lectureId: number) {
    this.getGameByLecture(lectureId).subscribe({
      next: (v) => this.game.set(v),
      error: (e) => e.log(),
    })
  }

  getGame(id: number) :Observable<Game> {
    return this.http.get<Game>(backendURL() + '/game/' + id);
  }

  setGame(id: number) {
    this.getGame(id).subscribe({
      next: (v) => this.game.set(v),
      error: (e: Error) => console.error(e),
    })
  }

  getCurrentGame() {
    return computed(() => this.game());
  }

  createGame(lectureId: number) : Observable<number> {
    return this.http.post<number>(backendURL() + '/game/lecture/' + lectureId + '/current', {});
  }

  joinGame(id: number) {
    this.http.post<Game>(backendURL() + '/game/join/' + id, {}).subscribe({
      next: (v) => this.game.set(v),
      error: (e: Error) => console.error(e),
    });
  }

  setBoard() {
    this.userService.getUser().subscribe((v) => {
      console.log(v)
      if (!this.game()) return;
      for (let p of this.game()!.participants) {
        if (p.user.id === v.id) {
          this.board.set(p.bingo);
        }
      }
    })
    return computed(() => this.board());
  }

  clickCard(pos: number) {
    this.http.post(backendURL() + '/game/clickcard/' + pos, {}).subscribe({
      error: (e) => console.error(e),
    });
  }

}