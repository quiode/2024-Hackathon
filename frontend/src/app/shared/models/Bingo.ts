import { Card } from './Card';
import { Game } from './Game';
import { User } from './User';

export interface Bingo {
 id: number;
 owner: User;
 game: Game;
 height: number;
 width: number;
 cardMatrix: Card[];
}