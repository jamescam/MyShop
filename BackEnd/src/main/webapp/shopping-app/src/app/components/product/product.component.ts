import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../../models/Product';
import { Transaction } from '../../models/Transaction';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  transaction: Transaction={user:{balance:0},quantity:0, product:{productPrice:0, productName:""}};
  quantity: number = 0;
  hidden: boolean = false;
  added: boolean = false;

  @Input()
  productData!: Product;

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
  }


  public addProductToCart() {
    var token = sessionStorage.getItem("Authorization");
    if(token){
      if(this.quantity > 0){
        const arr = token.split(":");
        this.transaction.user.userId = Number(arr[0]);
        this.transaction.product.productId = this.productData.productId;
        this.transaction.quantity = this.quantity;
        this.productService.addProductToCart(this.transaction).subscribe(
          (result)=>console.log(result)
        )
        this.hidden = false;
        this.added = true;
      }else{
        this.hidden = true;
        this.added = false;
      } 
    }else{
      this.router.navigate(['/login'])
    }
  }

}
