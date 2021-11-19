import { NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';

import { FormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { AppRoutingModule } from './app-routing.module';

import { HttpClientModule } from  '@angular/common/http';
import { ProductListComponent } from './components/product-list/product-list.component';
import { CartComponent } from './components/cart/cart.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MaterialModule } from './material.module';
import { NaviComponent } from './components/navi/navi.component';
import { ProductComponent } from './components/product/product.component';
import { CartTransactionComponent } from './components/cart-transaction/cart-transaction.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HistoryTransactionComponent } from './components/history-transaction/history-transaction.component';
import { ManagerHomeComponent } from './components/manager-home/manager-home.component';
import { CustomerListComponent } from './components/customer-list/customer-list.component';
import { ManageProductComponent } from './components/manage-product/manage-product.component';



@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    ProductListComponent,
    CartComponent,
    NaviComponent,
    ProductComponent,
    CartTransactionComponent,
    ProfileComponent,
    HistoryTransactionComponent,
    ManagerHomeComponent,
    CustomerListComponent,
    ManageProductComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
