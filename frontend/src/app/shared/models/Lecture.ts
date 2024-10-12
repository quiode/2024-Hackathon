import { Professor } from "./Professor"
import { Timeframe } from "./Timeframe"

export interface Lecture {
  id: number,
  title: string,
  lecturers: Professor[],
  dates: Timeframe[],
  cards: number[]
}