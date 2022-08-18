import {Component, OnInit, ViewChild} from '@angular/core';
import { AuthService } from 'src/app/shared/auth.service';
import { NotificationsService, NotificationType } from 'angular2-notifications';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { environment } from 'src/environments/environment';
import {UserModel} from '../user.model';
import {UserService} from '../user.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
})
export class RegisterComponent{
  user: UserModel = new UserModel();
  @ViewChild('registerForm') registerForm: NgForm;
  buttonDisabled = false;
  buttonState = '';

  constructor(private authService: AuthService,
              private notifications: NotificationsService,
              private router: Router,
              private us: UserService) { }

  onSubmit(): void {
    if (this.registerForm.valid && !this.buttonDisabled) {
      this.buttonDisabled = true;
      this.buttonState = 'show-spinner';
      this.us.addUser(this.user).subscribe(() => {
        this.router.navigate(['/user/login']);
      }, (err: HttpErrorResponse) => {
        this.notifications.create('Error', err.message, NotificationType.Bare,
          { theClass: 'outline primary', timeOut: 6000, showProgressBar: false });
        this.buttonDisabled = false;
        this.buttonState = '';      });

    }
  }
}
