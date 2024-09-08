import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-assignment-upload',
  templateUrl: './assignment-upload.component.html',
  styleUrls: ['./assignment-upload.component.css']
})
export class AssignmentUploadComponent {
  assignmentUrl: SafeResourceUrl | undefined;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {}

  loadAssignmentFile(assignmentId: number) {
    this.http.get(`http://localhost:5555/assignments/showAssignmentFileById/${assignmentId}`, { responseType: 'blob' })
      .subscribe(response => {
        const url = window.URL.createObjectURL(response);
        this.assignmentUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
      });
  }
}
