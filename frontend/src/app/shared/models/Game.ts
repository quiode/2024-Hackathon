import { Lecture } from "./Lecture";
import { Timeframe } from "./TimeFrame";

export interface Game {
  id: number,
  lectureId: number,
  lecturer: number,
  timeframe: Timeframe
}