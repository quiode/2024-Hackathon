import { Timeframe } from "./Timeframe"

export interface Lecture {
  id: number,
  title: string,
  lecturers: number[],
  dates: Timeframe[],
  cards: number[]
}