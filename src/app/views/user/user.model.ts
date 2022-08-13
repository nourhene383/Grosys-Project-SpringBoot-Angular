import {RoleModel} from "./role.model";

export class UserModel {
  id: number;
  fullname: string;
  email: string;
  password: string;
  tel: number;
  roles: RoleModel;
}
