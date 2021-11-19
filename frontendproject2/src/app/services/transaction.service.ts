import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Transaction } from '../models/Transaction';
import { Observable } from 'rxjs';
import { BaseURLService } from './base-url.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  baseServerURL: string = this.urlService.getServerBaseURL();

  constructor(private httpClient: HttpClient, private urlService: BaseURLService ) {}

  public getCartTransactions(): Observable<Transaction[]>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.get<Transaction[]>(`${this.baseServerURL}/unpaidtransactions`, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.get<Transaction[]>(`${this.baseServerURL}/unpaidtransactions`, {headers: {}});
    }
  }

  public getPaidTransactions(): Observable<Transaction[]>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.get<Transaction[]>(`${this.baseServerURL}/paidtransactions`, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.get<Transaction[]>(`${this.baseServerURL}/paidtransactions`, {headers: {}});
    }
  }

  public getPaidTransactionsById(id: number): Observable<Transaction[]>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.get<Transaction[]>(`${this.baseServerURL}/paidtransactions/${id}`, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.get<Transaction[]>(`${this.baseServerURL}/paidtransactions/${id}`, {headers: {}});
    }
  }

  public updateTransactionToPaid(transaction: Transaction): Observable<boolean>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.put<boolean>(`${this.baseServerURL}/transaction`,transaction, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.put<boolean>(`${this.baseServerURL}/transaction`,transaction, {headers: {}});
    }
  }

  public deleteTransactionById(id: number): Observable<boolean>{
    var token = sessionStorage.getItem("Authorization");
    if(token){
      return this.httpClient.delete<boolean>(`${this.baseServerURL}/transaction/${id}`, {headers: {"Authorization": token}});
    }else{
      return this.httpClient.delete<boolean>(`${this.baseServerURL}/transaction/${id}`, {headers: {}});
    }
  }

  public convertTransactionTime(transactionDate: number){
    var d = new Date(transactionDate);
    var formattedDate = (d.getMonth() + 1)  + "-"  +d.getDate()+  "-"+ d.getFullYear() ;
    var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
    var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
    var formattedTime = hours + ":" + minutes;

    formattedDate = formattedDate + " " + formattedTime;
    return formattedDate;
  }
}
