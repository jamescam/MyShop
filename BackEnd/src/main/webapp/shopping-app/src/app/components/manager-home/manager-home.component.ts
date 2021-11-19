import { Component, OnInit } from '@angular/core';
import { Product } from '../../models/Product';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {
  
  constructor(private router: Router,private productService: ProductService){}


  productListData: Product[] = [];
  newProduct: Product = {productPrice:0};
  name:string = "";
  price:number=0.00;
  hidden:boolean= false;

  ngOnInit(): void {

    //this.productListData = this.productService.getAllProducts();
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

  addNewProduct(){
    this.newProduct.productName = this.name;
    this.newProduct.productPrice = this.price;
    this.productService.addNewProduct(this.newProduct).subscribe(
      (result)=>{
        if(result){
          location.reload();
        }else{
          this.hidden = true;
        }
      }
    )
  }

}
