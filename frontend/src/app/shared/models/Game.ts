import { Lecture } from "./Lecture";
import { Timeframe } from "./Timeframe";

export interface Game {
  id: number,
  lectureId: number,
  lecturer: number,
  timeframe: Timeframe
}