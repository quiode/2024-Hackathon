import { Injectable, signal } from '@angular/core';
import { Lecture } from '../models/Lecture';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, Observable, of, tap } from 'rxjs';
import { backendURL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class LectureService {
  private lecture = signal<Lecture | null>(null);

  constructor(private http: HttpClient) { }

  getLecture(id: number): Observable<Lecture> {
    let lecture = this.lecture();

    // if (!lecture) {
    //   return this.http.get<Lecture>(backendURL() + "/lecture", {
    //     params: {
    //       id: id
    //     }
    //   }).pipe(tap(val => this.lecture.set(val)));
    // } else {
    //   return of(lecture);
    // }
    return of({
      name: 'Mathematische Methoden der Physik',
      dates: [],
      id: 1,
      professors: []
    } satisfies Lecture)
  }
}
