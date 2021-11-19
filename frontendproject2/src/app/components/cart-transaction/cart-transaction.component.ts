import { Component, OnInit, Input } from '@angular/core';
import { Transaction } from '../../models/Transaction';
import { TransactionService } from '../../services/transaction.service';

@Component({
  selector: 'app-cart-transaction',
  templateUrl: './cart-transaction.component.html',
  styleUrls: ['./cart-transaction.component.css']
})
export class CartTransactionComponent implements OnInit {
 

  @Input()
  transactionData!: Transaction;


  constructor(private transacionService: TransactionService) { }

  ngOnInit(): void {

  }

  deleteTransaction(){
    if(this.transactionData.transactionId)
    this.transacionService.deleteTransactionById(this.transactionData.transactionId).subscribe(
      (result)=>{
        location.reload();
      }
    )
  }
}
