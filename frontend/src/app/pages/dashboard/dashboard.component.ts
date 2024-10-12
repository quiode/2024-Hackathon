import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { LectureCardComponent } from '../lecture/lecture-card/lecture-card.component';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faBolt} from "@fortawesome/free-solid-svg-icons";
import {Lecture} from "../../shared/models/Lecture";
import {NgForOf} from "@angular/common";
import {Observable} from "rxjs";
import {Game} from "../../shared/models/Game";

@Component({
  selector: 'app-dashbord',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, LectureCardComponent, FaIconComponent, NgForOf],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})



export class DashboardComponent {
  ngOnInit() {
  }

  protected readonly faBolt = faBolt;
  protected readonly getUpcoming = getUpcoming;
  protected readonly getLectures = getLectures;
}

function getUpcoming(): Lecture[] {
  return getLectures().filter(lecture => (isUpcoming(lecture.date)));
}

function getLectures(): Lecture[] {
  return [];
}
