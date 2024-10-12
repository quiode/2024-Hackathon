import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { backendURL } from '../constants';
import { Game } from '../models/Game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private lecture = signal<Game | null>(null);

  constructor(private http: HttpClient) { }

  getGame(id: number): Observable<Game> {
    let lecture = this.lecture();

    return of({
      id: 1,
      lectureId: 1,
      lecturer: 1,
      timeframe: {start: 1000, end: 2000}
    })
  }
}