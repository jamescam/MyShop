import { Component, OnInit, Input } from '@angular/core';
import { RegisterService } from '../../services/register.service';
import { Router } from '@angular/router';
import { User } from '../../models/User';
import { HttpHeaders } from  '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input()
  //user!: User;
  user: User = {balance:0};
  email = "";
  password = "";
  hidden: boolean = false;



  constructor(private router: Router, public registerService:RegisterService) { }

  ngOnInit(): void {
  }

  loginUser(){
    if(this.password != "" && this.email != ""){
      this.user.email = this.email;
      this.user.password = this.password;

      this.registerService.getUser(this.user).subscribe(
        (data) => {
    
          if(data.body.manager){
            sessionStorage.setItem("Authorization",data.headers.get("Authorization"));
            this.router.navigate(['/manager'])

          }else{
            sessionStorage.setItem("Authorization",data.headers.get("Authorization"));
            this.router.navigate(['/products'])}
          },
        (error) => {
          this.hidden = true;
      });
    }
  }
}
