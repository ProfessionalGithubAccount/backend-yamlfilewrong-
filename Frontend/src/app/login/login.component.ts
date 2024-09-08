import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  submit(): void{
    alert("Login Successfull")
  }
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  login() {
    // this.authService.loginStudent(this.username, this.password).subscribe(
    //   response => {
        this.authService.loginStudent(this.username, this.password).subscribe(
          response => {
              console.log('Login successful:', response);
              this.router.navigate(['/dashboard']);
          },
          error => {
              console.error('Login failed:', error);
          }
      );
      
        // console.log('Login successful:', response);
        this.router.navigate(['/dashboard']); // Navigate to the dashboard on success
    //   },
    //   error => {
    //     console.error('Login failed:', error);
    //     this.errorMessage = 'Invalid username or password';
    //   }
    // );
  }
}
