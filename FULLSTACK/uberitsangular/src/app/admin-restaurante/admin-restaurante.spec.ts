import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminRestaurante } from './admin-restaurante';

describe('AdminRestaurante', () => {
  let component: AdminRestaurante;
  let fixture: ComponentFixture<AdminRestaurante>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminRestaurante],
    }).compileComponents();

    fixture = TestBed.createComponent(AdminRestaurante);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
