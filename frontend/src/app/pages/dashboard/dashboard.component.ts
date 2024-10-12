import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LectureCardComponent } from '../lecture/lecture-card/lecture-card.component';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faBolt} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-dashbord',
  standalone: true,
    imports: [RouterOutlet, RouterLink, RouterLinkActive, LectureCardComponent, FaIconComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  protected readonly faBolt = faBolt;
}
