import { Component, computed, input, Signal, signal } from '@angular/core';
import { LectureTimeframe } from '../../shared/models/LectureTimeframe';

@Component({
  selector: 'app-lecture-timeframe',
  standalone: true,
  imports: [],
  templateUrl: './lecture-timeframe.component.html',
  styleUrl: './lecture-timeframe.component.css'
})
export class LectureTimeframeComponent {
  lectureTimeframe = input.required<LectureTimeframe | undefined>();
  showWeekdays = input<boolean>(false);
  start: Signal<Date | undefined>;
  end: Signal<Date | undefined>;

  constructor() {
    this.start = computed(() => {
      if (this.lectureTimeframe() === undefined) return undefined;
      return new Date(this.lectureTimeframe()!.startDate);
    });
    this.end = computed(() => {
      if (this.lectureTimeframe() === undefined) return undefined;
      return new Date(this.lectureTimeframe()!.endDate);
    });
  }

  getWeekDay(day: number | undefined) {
    if (day == undefined) return '';
    return ['Sun.', 'Mon.', 'Tue.', 'Wed.', 'Thu.', 'Fri.', 'Sat.'][day];
  }
}
