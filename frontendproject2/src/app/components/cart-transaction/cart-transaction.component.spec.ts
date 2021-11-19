import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartTransactionComponent } from './cart-transaction.component';

describe('CartTransactionComponent', () => {
  let component: CartTransactionComponent;
  let fixture: ComponentFixture<CartTransactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CartTransactionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CartTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
