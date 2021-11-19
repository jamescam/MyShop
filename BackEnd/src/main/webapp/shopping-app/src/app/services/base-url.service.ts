import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BaseURLService {

  baseServerURL: string = "http://ec2-3-139-93-134.us-east-2.compute.amazonaws.com:8080/MyShop";
  //baseServerURL: string = "http://localhost:8082/project2";


  constructor() { }

  public getServerBaseURL(){
    return this.baseServerURL;
  }

  // public getWebAppBaseURL(){
  //   return this.baseWebAppURL;
  // }
}
