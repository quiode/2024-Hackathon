import { computed, effect, Injectable, Signal, signal } from '@angular/core';
import { Lecture } from '../models/Lecture';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, combineLatest, Observable, of, tap } from 'rxjs';
import { backendURL } from '../constants';
import { createWatch } from '@angular/core/primitives/signals';

@Injectable({
  providedIn: 'root'
})
export class LectureService {
  private lectures = signal<Lecture[]>([]);

  constructor(private http: HttpClient) {
    this.fetchLectures();
   }

  getLecture(id: number): Signal<Lecture | undefined> {
    return computed(() => this.lectures().find(lecture => lecture.id === id));
  }

  getLectures(): Signal<Lecture[]> {
    return this.lectures.asReadonly();
  }

  fetchLectures() {
    this.http.get<Lecture[]>(backendURL() + "/lecture").subscribe(lectures => this.lectures.set(lectures));
  }
}
