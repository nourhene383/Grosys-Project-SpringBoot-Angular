import {HttpClient} from "@angular/common/http";
import {UserModel} from "./user.model";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class LoginuserService {
  constructor(private http: HttpClient) {
  }

  loginUser(username: string, password: string){
   return this.http.post<UserModel>('http://127.0.0.1:8082/api/users',
      {
        username : username,
        password : password
      }
    );

  }
}
