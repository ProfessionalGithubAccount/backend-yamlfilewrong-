import { Component } from '@angular/core';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {
  courses = [
    { name: 'Java OOPS Concepts', year: '1st', semester: '2nd', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jpg' },
    { name: 'Depp Learning', year: '2nd', semester: '1st', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jpg' },
    { name: 'Course-3', year: '1st', semester: '1st', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jpg' },
    { name: 'Course-4', year: '3rd', semester: '2nd', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jpg' },
    { name: 'Course-5', year: '2nd', semester: '2nd', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jp' },
    { name: 'Course-6', year: '4th', semester: '1st', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jp' },
    { name: 'Course-7', year: '3rd', semester: '1st', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jp' },
    { name: 'Course-8', year: '4th', semester: '2nd', image: 'https://cdn.slidesharecdn.com/ss_thumbnails/oops-230509045251-8151d39e-thumbnail.jp' }
  ];
}
