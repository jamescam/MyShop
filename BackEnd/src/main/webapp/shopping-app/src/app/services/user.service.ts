import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Amount } from '../models/Amount';
import { User } from '../models/User';
import { BaseURLService } from './base-url.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseServerURL: string = this.urlService.getServerBaseURL();

  constructor(private httpClient: HttpClient, private urlService: BaseURLService) { }

  public getUserById(): Observable<User>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      var arr = token.split(":");
      var userId = Number(arr[0]);
      return this.httpClient.get<User>(`${this.baseServerURL}/user/${userId}`, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.get<User>(`${this.baseServerURL}/user`, {headers: {}});
    }
  }

  public depositByUserId(amount: Amount): Observable<boolean>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.put<boolean>(`${this.baseServerURL}/updatebalance`,amount, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.put<boolean>(`${this.baseServerURL}/updatebalance`,amount, {headers: {}});
    }
  }

  public getAllCustomersByManager(): Observable<User[]>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.get<User[]>(`${this.baseServerURL}/alluser`, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.get<User[]>(`${this.baseServerURL}/alluser`, {headers: {}});
    }
  }

  public validateUser(data: User): Observable<any> {
    return this.httpClient.post(`${this.baseServerURL}/user`, data, {observe: 'response'});
  }

  public updatePassword(user: User): Observable<boolean>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.put<boolean>(`${this.baseServerURL}/password`,user, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.put<boolean>(`${this.baseServerURL}/password`,user, {headers: {}});
    }
  }
}
