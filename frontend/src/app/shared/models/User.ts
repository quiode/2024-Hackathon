import { Game } from './Game';
import { Lecture } from './Lecture';

export interface User {
  id: number;
  name: string;
  mail: string;
  lectures: Lecture[]; // TODO
  currentGame: Game | null; // TODO
}