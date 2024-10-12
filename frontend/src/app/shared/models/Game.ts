import { Lecture } from "./Lecture";
import { LectureTimeframe } from './LectureTimeframe';
import { Professor } from './Professor';

export interface Game {
  id: number,
  lecture: Lecture,
  professor: Professor,
  timeframe: LectureTimeframe
}