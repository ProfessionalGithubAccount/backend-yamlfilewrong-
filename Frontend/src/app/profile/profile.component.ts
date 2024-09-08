import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  student: any = {};

  constructor(private http: HttpClient, private authService: AuthService) {}

  ngOnInit(): void {
    const studentId = this.authService.getStudentId(); // Get the student ID from AuthService
    this.http.get<any>(`http://localhost:5555/user/studentprofileDetails/${studentId}`)
      .subscribe(data => {
        this.student = data;
      }, error => {
        console.error('Failed to load student details:', error);
      });
  }
}
