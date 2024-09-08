import { Component } from '@angular/core';

@Component({
  selector: 'app-profregistration',
  templateUrl: './profregistration.component.html',
  styleUrls: ['./profregistration.component.css']
})
export class ProfregistrationComponent {
  submit(){
    alert("Registration Successful");
  }
}
