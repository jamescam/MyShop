export class Product{
    productId?:number;
    productName?:string;
    productPrice:number;

    constructor(productId:number, productName:string, productPrice:number){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}