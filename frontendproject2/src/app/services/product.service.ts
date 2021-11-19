import { Injectable } from '@angular/core';
import { Product } from '../models/Product';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Transaction } from '../models/Transaction';
import { BaseURLService } from './base-url.service';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseServerURL: string = this.urlService.getServerBaseURL();

  constructor(private httpClient: HttpClient, private urlService: BaseURLService) { }

  public getAllProducts(): Observable<Product[]>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.get<Product[]>(`${this.baseServerURL}`+"/product", {headers: {"Authorization": token}});
    }else{
      return this.httpClient.get<Product[]>(`${this.baseServerURL}`+"/product", {headers: {}});
    }
  }

  public addProductToCart(transaction: Transaction): Observable<boolean>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.post<boolean>(`${this.baseServerURL}`+"/transaction", transaction, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.post<boolean>(`${this.baseServerURL}`+"/transaction", {headers: {}});
    }
  }

  public addNewProduct(newProduct: Product): Observable<boolean>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.post<boolean>(`${this.baseServerURL}`+"/product", newProduct, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.post<boolean>(`${this.baseServerURL}`+"/product", {headers: {}});
    }
  }

  
}
