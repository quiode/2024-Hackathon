import { Injectable, signal } from '@angular/core';
import { Lecture } from '../models/Lecture';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { backendURL } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class LectureService {
  private lecture = signal<Lecture | null>(null);

  constructor(private http: HttpClient) { }

  getLecture(): Observable<Lecture> {
    let lecture = this.lecture();

    // if (!lecture) {
    //   return this.http.get<Lecture>(backendURL() + "/lecture").pipe(tap(val => this.lecture.set(val)));
    // } else {
    //   return of(lecture);
    // }
    return of({
      title: 'Mathematische Methoden der Physik',
      lecturers: ['Peter Hinz, Alessio Figalli'],
      start: 1000,
      end: 2000,
      cards: [1, 2]
    })
  }
}
