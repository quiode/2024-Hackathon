import { Timeframe } from "./Timeframe"

export interface Lecture {
  id: number,
  title: String,
  lecturers: Array<number>,
  dates: Array<Timeframe>
  cards: Array<number>
}