import { Card } from "./Card";

export interface Bingo {
  id: number,
  width: number,
  height: number,
  cards: Card[],
  ntValidated: number[],
}