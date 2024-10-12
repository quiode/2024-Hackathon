import { Lecture } from './Lecture';
import { Professor } from './Professor';
import { User } from './User';

export interface Card {
  id: number;
  text: string;
  creationDate: number;
  creator: User;
  upvotes: User[];
  downvotes: User[];
  lecture: Lecture;
  professor: Professor;
}