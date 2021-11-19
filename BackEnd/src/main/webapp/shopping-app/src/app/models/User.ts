export class User{

    userId?:number;
    firstName?:string;
    lastName?:string;
    password?:string;
    email?:string;
    manager?: boolean;
    balance:number;

    constructor(firstName:string, lastName:string, password:string, email:string, manager:boolean, balance:number, userId:number){
        
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.manager = manager; 
        this.balance = balance;
    }
    
}