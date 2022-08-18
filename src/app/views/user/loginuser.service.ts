import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class LoginuserService {
  readonly rooturl = 'http://127.0.0.1:8082';

  constructor(private http: HttpClient) {
  }

  // tslint:disable-next-line:typedef
  loginUser(username, password) {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);
    const options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };
    const payload = new HttpParams()
      .set('username', username)
      .set('password', password);
    return this.http.post(this.rooturl + '/api/login', body.toString(), options);

  }
}
