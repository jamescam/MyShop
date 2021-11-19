export class Product{
    productId?:number;
    productName?:string;
    productPrice:number;
    url: string = "";
    hidden: boolean = false;
    added: boolean = false;

    constructor(productId:number, productName:string, productPrice:number, url: string, hidden: boolean, added: boolean){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.url = url;
    }
}