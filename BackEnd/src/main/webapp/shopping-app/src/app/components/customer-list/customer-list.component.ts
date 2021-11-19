import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user.service';
import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../models/Transaction';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customerListData:User[]=[];
  transactionListData:Transaction[]=[];
  hidden:boolean=false;
  checkId:number=0;


  constructor(private userService: UserService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.userService.getAllCustomersByManager().subscribe(
      (result)=>{
        this.customerListData = result;
      }
    )
  }

  viewTransaction(userId: number){
    console.log(userId)
    this.transactionService.getPaidTransactionsById(userId).subscribe(
      (result)=>{
        this.transactionListData = result;
        for(let ts of this.transactionListData){
          if(ts.transactionDate){
            ts.transactionDate = this.transactionService.convertTransactionTime(Number(ts.transactionDate)).toString();
          }
        }
        this.checkId=userId;
        this.hidden = true;
      }
    )
  }
}
