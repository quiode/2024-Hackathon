import { Card } from "./Card";
import { Lecture } from "./Lecture";
import { LectureTimeframe } from './LectureTimeframe';
import { Participant } from "./Participant";
import { Professor } from './Professor';

export interface Game {
  id: number,
  lecture: Lecture,
  professor: Professor,
  lectureTimeframe: LectureTimeframe,
  participants: Participant[],
  cardPool: Card[],
  bingoWidth: number,
  bingoHeight: number
}