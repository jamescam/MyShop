import { Component, OnInit } from '@angular/core';
import { Transaction } from '../../models/Transaction';
import { TransactionService } from '../../services/transaction.service';

@Component({
  selector: 'app-history-transaction',
  templateUrl: './history-transaction.component.html',
  styleUrls: ['./history-transaction.component.css']
})
export class HistoryTransactionComponent implements OnInit {

  paidTransactionListData: Transaction[] = [];
  time: string  = "";
  
  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.transactionService.getPaidTransactions().subscribe(
      (result)=>{
        this.paidTransactionListData = result;
        for(let ts of this.paidTransactionListData){
          if(ts.transactionDate){
            ts.transactionDate = this.transactionService.convertTransactionTime(Number(ts.transactionDate)).toString();
          }
        }
      }
    );   
  }

}
