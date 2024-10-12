import { LectureTimeframe } from './LectureTimeframe';
import { Professor } from './Professor';

export interface Lecture {
  id: number;
  name: string;
  professors: Professor[];
  dates: LectureTimeframe[];
}