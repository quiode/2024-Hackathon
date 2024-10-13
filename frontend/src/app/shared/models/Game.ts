import { Card } from "./Card";
import { Lecture } from "./Lecture";
import { LectureTimeframe } from './LectureTimeframe';
import { Professor } from './Professor';
import { User } from "./User";

export interface Game {
  id: number,
  lecture: Lecture,
  professor: Professor,
  timeframe: LectureTimeframe,
  users: User[],
  cardpool: Card[],
  bingoWidth: number,
  bingoHeight: number
}