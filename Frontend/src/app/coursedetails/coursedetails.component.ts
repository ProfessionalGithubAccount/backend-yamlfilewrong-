import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { YoutubeService } from '../youtube.service';

@Component({
  selector: 'app-coursedetails',
  templateUrl: './coursedetails.component.html',
  styleUrls: ['./coursedetails.component.css']
})
export class CoursedetailsComponent implements OnInit{
  // courseName: string = ''; // This will hold the current course name

  // constructor(private route: ActivatedRoute) {}

  // ngOnInit(): void {
  //   this.route.params.subscribe(params => {
  //     this.courseName = params['courseName']; // Assuming 'courseName' is a route param
  //     // Additional logic to handle course details if needed
  //   });
  // }
  showAssignments: boolean = false; // Default is showing resources

  // resources = ['Resource 1', 'Resource 2', 'Resource 3', 'Resource 4','Resource 5', 'Resource 6'];
  assignments = ['Assignment 1', 'Assignment 2', 'Assignment 3', 'Assignment 4'];

  toggleView(view: string): void {
    this.showAssignments = view === 'assignments';
  }
  videos: any[] = [];
  courseName: string ="course";

  constructor(private route: ActivatedRoute, private youtubeService: YoutubeService) { }

  ngOnInit(): void {
    // Retrieve the course name from query parameters
    this.route.queryParams.subscribe(params => {
      this.courseName = params['courseName'];
      // console.log('Course Name:', this.courseName);
      // Use this.courseName for further logic
      this.fetchVideos(this.courseName);
    });
  }

  // Define the fetchVideos method
  fetchVideos(courseName: string): void {
    this.youtubeService.getVideos(courseName).subscribe(
      (response) => {
        this.videos = response; // Assuming response structure fits your model
      },
      (error) => {
        console.error('Error fetching videos:', error);
      }
    );
  }
}
