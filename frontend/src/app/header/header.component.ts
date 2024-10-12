import { Component, computed } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faMoon, faSun } from '@fortawesome/free-solid-svg-icons';
import { ThemeService } from '../shared/services/theme.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [FontAwesomeModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  theme;
  themeIcon;
  constructor(private themeService: ThemeService) {
    this.theme = this.themeService.getTheme();
    this.themeIcon = computed(() => {
      if (this.theme() == 'dark') {
        return faSun;
      } else {
        return faMoon;
      }
    });
  }

  onThemeChange() {
    this.themeService.setTheme(this.theme() == 'dark' ? 'light' : 'dark');
  }
}
