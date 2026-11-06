import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminRestaurantes } from './admin-restaurantes';

describe('AdminRestaurantes', () => {
  let component: AdminRestaurantes;
  let fixture: ComponentFixture<AdminRestaurantes>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminRestaurantes],
    }).compileComponents();

    fixture = TestBed.createComponent(AdminRestaurantes);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
