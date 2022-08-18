import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {UserModel} from './user.model';

@Injectable({providedIn: 'root'})
export class UserService {
  readonly rooturl = 'http://127.0.0.1:8082';

  constructor(private http: HttpClient) {
  }

  // tslint:disable-next-line:typedef
  addUser(user: UserModel) {
    return this.http.post<UserModel>(this.rooturl + '/api/user/save', user);

  }
}
