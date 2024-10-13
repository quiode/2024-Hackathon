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
    return this.http.get<Game>('/game/lecture/' + lectureId + '/current');
  }

  setGameByLecture(lectureId: number) {
    this.getGameByLecture(lectureId).subscribe({
      next: (v) => this.game.set(v),
      error: (e) => e.log(),
    })
  }

  getGame(id: number) {
    const tc: Card = {
      id: 1,
      text: 'gehtauchsoiglolhahaha',
      creationDate: new Date().toUTCString(),
      creator: {
        id: 1,
        mail: 'test@ethz.ch',
        name: 'Diese Daten haben alle lol',
        lectures: []
      },
      upvotes: [],
      downvotes: [],
      lecture: {
        id: 1,
        name: 'leck mich',
        professors: [],
        dates: []
      },
      professor: {
        id: 1,
        name: 'test',
      }
    };
    return of({
      id: 1,
      lectureTimeframe: {startDate: new Date().toUTCString(), endDate: new Date().toUTCString(), id: 1},
      lecture: {
        dates: [],
        id: 1,
        name: 'Mathematische Funktion der Physik',
        professors: []
      },
      professor: {
        id: 1, 
        name: 'Ueli'
      },
      participants: [
        {
          id: 1,
          user: {
            id: 1,
            mail: 'test@ethz.ch',
            name: 'Diese Daten haben alle lol',
            lectures: []
          },
          bingo: {
            id: 1,
            width: 4,
            height: 4,
            cards: Array(16).fill(tc),
            ntValidated: Array(16).fill(0)
          }
        }
      ],
      cardPool: [],
      bingoWidth: 4,
      bingoHeight: 4
    } satisfies Game);
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
    return this.http.post<number>('/game/lecture/' + lectureId + '/current', {});
  }

  joinGame(id: number) {
    this.http.post<Game>('/game/join/' + id, {}).subscribe({
      next: (v) => this.game.set(v),
      error: (e: Error) => console.error(e),
    });
  }

  setBoard() {
    let id = -1;
    this.userService.getUser().subscribe((v) => {
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
    this.http.post('/game/clickcard/' + pos, {}).subscribe({
      error: (e) => console.error(e),
    });
  }

}