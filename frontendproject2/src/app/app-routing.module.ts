import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import {CartComponent} from './components/cart/cart.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HistoryTransactionComponent } from './components/history-transaction/history-transaction.component';
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { ProductComponent } from './components/product/product.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent},
  { path: '', component: LoginComponent},
  { path: 'cart', component: CartComponent},
  { path: 'products', component: ProductComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'history', component: HistoryTransactionComponent},
  { path: 'manager', component: ManagerHomeComponent},
  { path: 'customerlist', component: CustomerListComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
})
export class AppRoutingModule { }
