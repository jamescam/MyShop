import { Component, OnInit } from '@angular/core';
import { CartTransactionComponent } from '../cart-transaction/cart-transaction.component';
import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../models/Transaction';
import { Router } from '@angular/router';
import { transition } from '@angular/animations';
import { UserService } from '../../services/user.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  transactionListData: Transaction[] = [];
  balance: number=0.00;
  failed: boolean = false;
  emptyCart: boolean = false;
 

  constructor(private router: Router,private transactionService: TransactionService, private userService: UserService) { }

  ngOnInit(): void {
    
    var token = sessionStorage.getItem("Authorization");
    if(token){
      this.userService.getUserById().subscribe(
        (user)=>{
          console.log(user)
          if(user.balance)
          this.balance= user.balance}
      ) 
      
    this.transactionService.getCartTransactions().subscribe(
      (result) => {console.log(result)
        this.transactionListData = result}
    )
      }else{
        this.router.navigate(['/'])
      }
  }

  cashOut(){

    if(this.transactionListData.length>0){
      this.transactionService.updateTransactionToPaid(this.transactionListData[0]).subscribe(
        (result) => {location.reload()},
        (error) => {
          this.failed = true;
      })
    }
    else{
      this.emptyCart=true;
    }
  }
}

