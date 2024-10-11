import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DashbordComponent } from "./dashbord/dashbord.component";
import { HeaderComponent } from "./header/header.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, DashbordComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Hackathon 2024';
}
