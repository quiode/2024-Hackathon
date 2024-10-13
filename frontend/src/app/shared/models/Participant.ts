import { Bingo } from "./Bingo";
import { User } from "./User";

export interface Participant {
  id: number,
  user: User,
  bingo: Bingo
}