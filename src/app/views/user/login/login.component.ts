import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NotificationsService, NotificationType } from 'angular2-notifications';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/auth.service';
import { environment } from 'src/environments/environment';
import {Observable} from "rxjs";
import {UserModel} from "../user.model";
import {LoginuserService} from "../loginuser.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm: NgForm;
  emailModel = 'demo@Grosys.com';
  passwordModel = 'demogrosys1122';
  error: string = null;

  buttonDisabled = false;
  buttonState = '';

  constructor(private authService: LoginuserService, private notifications: NotificationsService, private router: Router) { }


  onSubmit(): void {
    if (this.loginForm.valid) {
      if (this.buttonDisabled) {
        const username = this.loginForm.value.email;
        const password = this.loginForm.value.password;
        let authObs: Observable<UserModel>;
        this.buttonDisabled = true;
        this.buttonState = 'show-spinner';
        authObs = this.authService.loginUser(username, password);
        authObs.subscribe(
          resData => {
            console.log(resData);
          },
          errorMessage => {
            console.log(errorMessage);
            this.error = errorMessage;
          }
        );
      }
    }
  }
}
