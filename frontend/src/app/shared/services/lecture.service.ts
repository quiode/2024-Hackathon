import { Injectable, signal } from '@angular/core';
import { Lecture } from '../models/Lecture';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LectureService {
  private lecture = signal<Lecture | null>

  constructor(private http: HttpClient) { }
}
