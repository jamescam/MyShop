import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from  '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { BaseURLService } from './base-url.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  baseServerURL: string = this.urlService.getServerBaseURL();

  constructor(private http: HttpClient, private urlService: BaseURLService) { }

  registerUser(data: User): Observable<any> {
    return this.http.post(`${this.baseServerURL}/newuser`, data)
  }

  getUser(data: User): Observable<any> {
    return this.http.post(`${this.baseServerURL}/user`, data, {observe: 'response'});
  }
  
}