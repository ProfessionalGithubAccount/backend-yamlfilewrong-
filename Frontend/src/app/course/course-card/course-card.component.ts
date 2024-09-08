import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-card',
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.css']
})
export class CourseCardComponent implements OnInit{
  @Input() course: any; 
  // studentId: number|null = this.authService.getStudentId();
  constructor(private router: Router) { }
  // courses: any[] = [];

  // constructor(
  //   private router: Router,
  //   private courseService: CourseService,
  //   private authService: AuthService
  // ) {}

  // ngOnInit(): void {
  //   this.authService.studentId$.subscribe(studentId => {
  //     this.studentId = studentId;
  //     if (this.studentId !== null) {
  //       this.loadCourses();
  //     }
  //   });
  // }

  // private loadCourses(): void {
  //   this.courseService.getCoursesByStudentId().subscribe(
  //     data => {
  //       this.courses = data;
  //       console.log('Courses:', this.courses);
  //     },
  //     error => {
  //       console.error('Error fetching courses', error);
  //     }
  //   );
  // }

  ngOnInit(): void {
    console.log(this.course);
  }

  fetchVideos(courseName: string): void {
    this.router.navigate(['/coursedetails'], { queryParams: { courseName } });
    console.log('Fetching videos for course in course card:', courseName);
  }
}
