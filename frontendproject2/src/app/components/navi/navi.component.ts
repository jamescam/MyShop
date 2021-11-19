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

  @Input()
  manager:boolean=false;

  @Input()
  customer:boolean=false;

  constructor(private router: Router, private urlService: BaseURLService) { }

  ngOnInit(): void {
    //this.baseWebAppURL = this.urlService.getWebAppBaseURL();
  }

  logoutUser()
  {
    sessionStorage.removeItem("Authorization");
    this.router.navigate(['/'])
  }


}
