import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-profcoursecard',
  templateUrl: './profcoursecard.component.html',
  styleUrls: ['./profcoursecard.component.css']
})
export class ProfcoursecardComponent {
  @Input() course: any; 
}
