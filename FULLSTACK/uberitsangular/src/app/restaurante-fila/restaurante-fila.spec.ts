import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RestauranteFila } from './restaurante-fila';

describe('RestauranteFila', () => {
  let component: RestauranteFila;
  let fixture: ComponentFixture<RestauranteFila>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestauranteFila],
    }).compileComponents();

    fixture = TestBed.createComponent(RestauranteFila);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
