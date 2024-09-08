import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ProfessorloginComponent } from './professor/professorlogin/professorlogin.component';
import { DashboardComponent } from './studentdashboard/dashboard.component';
import { CourseComponent } from './course/course.component';
import { ProfileComponent } from './profile/profile.component';
import { CoursedetailsComponent } from './coursedetails/coursedetails.component';
import { ResourcesComponent } from './coursedetails/resources/resources.component';
import { AssignmentComponent } from './professor/assignment/assignment.component';
import { AssignmentUploadComponent } from './assignment-upload/assignment-upload.component';
import { AdminloginComponent } from './admin/adminlogin/adminlogin.component';
import { AdmindashboardComponent } from './admin/admindashboard/admindashboard.component';
import { CoursesComponent } from './professor/professorcourses/courses.component';
import { ProfessordashboardComponent } from './professor/professordashboard/professordashboard.component';
import { ProfessorCoursedetailsComponent } from './professor/professor-coursedetails/professor-coursedetails.component';
import { ProfessorprofileComponent } from './professor/professorprofile/professorprofile.component';
import { ResourcecenterComponent } from './professor/resourcecenter/resourcecenter.component';
import { RegistrationComponent } from './registration/registration.component';
import { ProfregistrationComponent } from './registration/profregistration/profregistration.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {path: 'professorlogin', component: ProfessorloginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'course', component: CourseComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'coursedetails', component: CoursedetailsComponent},
  {path: 'resources', component: ResourcesComponent},
  {path: 'assignment', component: AssignmentComponent},
  {path: 'assignment-upload', component:AssignmentUploadComponent},
  {path: 'adminlogin', component: AdminloginComponent},
  {path: 'admindashboard', component: AdmindashboardComponent},
  {path: 'courses', component: CoursesComponent},
  {path: 'professordashboard', component: ProfessordashboardComponent},
  {path: 'professor-coursedetails', component: ProfessorCoursedetailsComponent},
  {path: 'professorprofile', component: ProfessorprofileComponent},
  {path: 'resourcecenter', component:ResourcecenterComponent},
  {path: 'registration', component:RegistrationComponent},
  {path: 'profregistration', component: ProfregistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
