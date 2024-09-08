import { Component } from '@angular/core';
import { CourseService } from '../course.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent {
  courses: any[] = [];
  studentId: number|null = this.authService.getStudentId()

  constructor(
    private courseService: CourseService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.studentId$.subscribe(studentId => {
      this.studentId = studentId;
      if (this.studentId !== null) {
        this.loadCourses();
      }
    });
  }

  private loadCourses(): void {
    this.courseService.getCoursesByStudentId().subscribe(
      data => {
        this.courses = data;
        console.log('Courses:', this.courses);
      },
      error => {
        console.error('Error fetching courses', error);
      }
    );
  }
}
