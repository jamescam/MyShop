import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransactionService } from 'src/app/services/transaction.service';
import { Product } from '../../models/Product';
import { Transaction } from '../../models/Transaction';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  transaction: Transaction={user:{balance:0},quantity:0, product:{productPrice:0, productName:"", url:"", hidden: false, added: false}};
  quantity: number = 0;
  productListData: Product[] = [];
  productData!: Product;
  transactionListData: Transaction[] = [];
  url: string = "";

  constructor(private productService: ProductService, private router: Router, private transactionService: TransactionService) { }

  ngOnInit(): void {
    var token = sessionStorage.getItem("Authorization");
    if(token){
    this.productService.getAllProducts().subscribe(
      (result) => {console.log(result)
        this.productListData = result}
    )
      }else{
        this.router.navigate(['/'])
      }
  }

   addProductToCart(card:Product) {
    var token = sessionStorage.getItem("Authorization");
    if(token){
      if(this.quantity > 0){
        const arr = token.split(":");
        this.transaction.user.userId = Number(arr[0]);
        this.transaction.product.productId = this.productData.productId;
        this.transaction.quantity = this.quantity;
        console.log(this.transaction);
        this.productService.addProductToCart(this.transaction).subscribe(
          (result)=>console.log(result)
        )
        card.hidden = false;
        card.added = true;
      }else{
        card.hidden = true;
        card.added = false;
      } 
    }
  }

  onChange(e: any) {
    this.quantity = e.target.value;
  }

  productD(product: Product){
    this.productData = product;  
  }
  
}

