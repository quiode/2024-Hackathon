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

  constructor(private http: HttpClient) { }

  getCardsByLecture(lecture: Lecture): Signal<Card[]> {
    // update value by values from db
    this.http.get<Card[]>(backendURL() + "/card/byLecture/" + lecture.id)
      .subscribe(
        cards => this.cardsByLecture.update(map => {
          let newMap = new Map(map);
          newMap.set(lecture.id, cards);
          return newMap;
        }));

    return computed(() => this.cardsByLecture().get(lecture.id) || []);
  }

  addCard(dto: CardAddDto): Observable<void> {
    return this.http.post<void>(backendURL() + "/card/create", {
      text: dto.text,
      lectId: dto.lecture.id,
      prof: dto.prof.id,
    }).pipe(
      tap(_ => this.getCardsByLecture(dto.lecture)()) // update locale stored cards
    );
  }
}

export interface CardAddDto {
  text: string;
  lecture: Lecture;
  prof: Professor;
}