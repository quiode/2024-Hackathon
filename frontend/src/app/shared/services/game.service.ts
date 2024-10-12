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
  private board = signal

  constructor(private http: HttpClient) { }

  getGame(id: number): Observable<Game> {
    let game = this.game();

    return of({
      id: 1,
      lectureId: 1,
      lecturer: 1,
      timeframe: {start: 1000, end: 2000}
    })
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

  }

  getBoard() {

  }

}