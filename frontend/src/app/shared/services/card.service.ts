import { Lecture } from './../models/Lecture';
import { Card } from './../models/Card';
import { HttpClient } from '@angular/common/http';
import { computed, Injectable, Signal, signal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { backendURL } from '../constants';
import { Observable, tap } from 'rxjs';
import { Professor } from '../models/Professor';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private cardsByLecture = signal<Map<number, Card[]>>(new Map());

  constructor(private http: HttpClient) {
  }

  fetchCardsByLecture(lecture: Lecture) {
    this.http.get<Card[]>(backendURL() + "/card/byLecture/" + lecture.id)
      .subscribe(
        cards => this.cardsByLecture.update(map => {
          let newMap = new Map(map);
          newMap.set(lecture.id, cards);
          return newMap;
        })
      );
  }

  getCardsByLecture(lecture: Lecture): Signal<Card[]> {
    // get cards if not already gotten
    if (!this.cardsByLecture().has(lecture.id)) {
      this.fetchCardsByLecture(lecture);
    }

    return computed(() => this.cardsByLecture().get(lecture.id) || []);
  }

  addCard(dto: CardAddDto): Observable<void> {
    return this.http.post<void>(backendURL() + "/card/create", {
      text: dto.text,
      lectId: dto.lecture.id,
      profId: dto.prof.id,
    }).pipe(
      tap(_ => this.fetchCardsByLecture(dto.lecture)) // update locale stored cards
    );
  }

  downvote(card: Card): Observable<void> {
    return this.http.post<void>(backendURL() + "/card/downvote/" + card.id, {});
  }

  upvote(card: Card): Observable<void> {
    return this.http.post<void>(backendURL() + "/card/upvote/" + card.id, {});
  }
}

export interface CardAddDto {
  text: string;
  lecture: Lecture;
  prof: Professor;
}