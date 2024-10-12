import { Time } from "./time";

export interface Lecture {
  title: String | null,
  lecturers: Array<number> | null,
  times: Array<Time> | null,
  cards: Array<number> | null
}
