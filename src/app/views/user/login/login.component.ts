import {Component, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import {NotificationsService, NotificationType} from 'angular2-notifications';
import {Router} from '@angular/router';
import {AuthService} from 'src/app/shared/auth.service';
import {environment} from 'src/environments/environment';
import {Observable} from "rxjs";
import {UserModel} from "../user.model";
import {LoginuserService} from "../loginuser.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm: NgForm;
  usernameModel = 'Grosys';
  passwordModel = 'demogrosys1122';
  error: string = null;
  isLoginError = false;
  buttonDisabled = false;
  buttonState = '';

  constructor(private authService: LoginuserService, private notifications: NotificationsService, private router: Router) {
  }


  onSubmit(username, password): void {
    this.authService.loginUser(username, password).subscribe(
      (data: any) => {
        localStorage.setItem('userToken', data.access_token);
        this.router.navigate(['/app/dashboards/default']);

      }, (err : HttpErrorResponse) =>{
        this.isLoginError = true;
      } );
  }
}
