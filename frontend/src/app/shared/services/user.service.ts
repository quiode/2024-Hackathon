import { Injectable, signal } from '@angular/core';
import { User } from '../models/User';
import { HttpClient } from '@angular/common/http';
import { backendURL } from '../constants';
import { lastValueFrom, Observable, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private user = signal<User | null>(null);

  constructor(private http: HttpClient) { }

  registerUser(): Observable<User> {
    return this.http.post<User>(backendURL() + "/user/register", {}).pipe(tap(val => this.user.set(val)));
  }

  getUser(): Observable<User> {
    let user = this.user();

    if (!user) {
      return this.http.get<User>(backendURL() + "/user").pipe(tap(val => this.user.set(val)));
    } else {
      return of(user);
    }
  }
}
