import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user.service';
import { Amount } from '../../models/Amount';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User = {balance:0};
  status: string = "";
  deposit: number = 0;
  amount : Amount = {};
  hidden: boolean = false;
  isManager: boolean = false;
  currentPassword: string = "";
  newPassword: string = "";
  newPasswordVerify: string = "";
  changeSuccessfully: boolean = false;
  changeFailed: boolean = false;
  


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserById().subscribe((result)=>{
      this.user = result;
      console.log(this.user.manager)

      if(this.user.manager){
        this.isManager =  true;
        this.status = "MANAGER"
      }else{
        this.status = "CUSTOMER"
      }
    })
  }

  depositBalance(){
    if(this.deposit>=0){
      this.amount.amount = this.deposit;
      this.userService.depositByUserId(this.amount).subscribe(
      (result) =>{
        if(result){
          location.reload()
        }else{
          this.hidden = true;
        }
      })}else{
      this.hidden = true;
    }
  }

  updatePassword(){
    if(this.newPassword==this.newPasswordVerify){
      this.user.password = this.newPassword;
      this.userService.updatePassword(this.user).subscribe(
        (result)=>{
          if(result){
            this.changeSuccessfully = true;
          }else{
            this.changeFailed = true;
          }
        }
      )
    }
  }

}
