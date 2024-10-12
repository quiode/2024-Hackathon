import { Component } from '@angular/core';
import { UserService } from '../shared/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private userService: UserService, private router: Router) { }

  onCreateAccount() {
    this.userService.registerUser().subscribe({
      next: val => {
        // navigate to dashboard
        this.router.navigate(['']);
      },
      error: err => {
        alert("There has been an error while creating a user: " + err);
      }
    });
  }
}
