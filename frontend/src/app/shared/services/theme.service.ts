import { effect, Injectable, signal } from '@angular/core';
import { toObservable } from '@angular/core/rxjs-interop';
import { argsArgArrayOrObject } from 'rxjs/internal/util/argsArgArrayOrObject';

/**
 * Provides functions to get/set the app theme
 */
@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private theme;

  constructor() {
    this.theme = signal<Theme>(localStorage.getItem('theme') || window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light');

    // on change update local storage
    effect(() => {
      localStorage.setItem('theme', this.theme());
    });

    // on change update classes
    effect(() => {
      if (this.theme() == 'dark') {
        document.documentElement.classList.add('dark');
      } else {
        document.documentElement.classList.remove('dark');
      }
    });
  }

  getTheme() {
    return this.theme.asReadonly();
  }

  setTheme(theme: Theme) {
    this.theme.set(theme);
  }
}

export type Theme = 'dark' | 'light';