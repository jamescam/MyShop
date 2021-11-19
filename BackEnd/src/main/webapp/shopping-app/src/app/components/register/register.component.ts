import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from '../../services/register.service';
import { User } from '../../models/User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = {balance:0};
  firstname: string = "";
  lastname: string = "";
  password: string ="";
  passwordVerify: string = "";
  email: string ="";
  hidden: boolean = false;
  missing: boolean = false;
  notmatch: boolean = false;
  
  constructor(private router: Router, public registerService:RegisterService) { }

  ngOnInit(): void {
  }

  registerUser(){
    if(this.firstname != "" && this.lastname != "" && this.password != "" && this.email != ""){

      if(this.password==this.passwordVerify){
        this.user.firstName = this.firstname;
        this.user.lastName = this.lastname;
        this.user.email = this.email;
        this.user.password = this.password;
        this.registerService.registerUser(this.user).subscribe(
        (data) => {
          this.router.navigate(['/']);
        },
        (error) => {
          this.hidden = true;
        }
      )
      }else{
        this.notmatch = true;
      }
    }else{
      this.missing = true;
    }       
  }   
}
