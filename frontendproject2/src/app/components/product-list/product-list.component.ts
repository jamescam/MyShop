import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/Product';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  constructor(private router: Router,private productService: ProductService){}


  productListData: Product[] = [];

  ngOnInit(): void {

    //this.productListData = this.productService.getAllProducts();
    var token = sessionStorage.getItem("Authorization");
    if(token){
    this.productService.getAllProducts().subscribe(
      (result) => {console.log(result)
        this.productListData = result;
      }
    )
      }else{
        this.router.navigate(['/'])
      }
  }

}
