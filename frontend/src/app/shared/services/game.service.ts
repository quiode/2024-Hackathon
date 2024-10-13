import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { backendURL } from '../constants';
import { Game } from '../models/Game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private game = signal<Game | null>(null);
  private liveGame = signal

  constructor(private http: HttpClient) { }

  getGame(id: number): Observable<Game> {
    let game = this.game();

    return of({
      id: 1,
      timeframe: {startDate: '', endDate: '', id: 1},
      lecture: {
        dates: [],
        id: 1,
        name: 'karl',
        professors: []
      },
      professor: {
        id: 1, 
        name: 'Ueli'
      },
      users: [],
      cardpool: [],
      bingoWidth: 4,
      bingoHeight: 4
    } satisfies Game)
  }

  createGame(lectureId: number) {
    let id = 1;
    if (id == null) {
      return null;
    }
    let game = this.game();
    return of(this.getGame(id));
  }

  joinGame() {
    let game = signal<Game | undefined>(undefined);
  }

  getBoard() {
    return {
      tiles: Array(16).fill(1)
    }
  }

}