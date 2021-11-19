import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BaseURLService } from 'src/app/services/base-url.service';

@Component({
  selector: 'app-navi',
  templateUrl: './navi.component.html',
  styleUrls: ['./navi.component.css']
})
export class NaviComponent implements OnInit {

  baseWebAppURL:string = "";
  customer: boolean = false;

  @Input()
  manager:boolean=false;

  // @Input()
  // customer:boolean=false;

  constructor(private router: Router, private urlService: BaseURLService) { }

  ngOnInit(): void {
    var token = sessionStorage.getItem("Authorization");
    if(token){
      this.customer = true;
    }
  }

  logoutUser()
  {
    sessionStorage.removeItem("Authorization");
  }


}
