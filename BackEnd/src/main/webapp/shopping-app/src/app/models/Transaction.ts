import { Timestamp } from "rxjs";
import { Product } from "./Product";
import { User } from "./User";

export class Transaction{
    transactionId?:number;
    user:User;
    product:Product;
    quantity:number;
    transactionDate?:string;
    is_paid?:boolean;

    constructor(transactionId:number, user:User, product:Product, quantity:number, transactionDate:string, is_paid:boolean){
        this.transactionId = transactionId;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.is_paid = is_paid;
    }
}