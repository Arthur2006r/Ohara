import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LidosPage } from './lidos.page';

describe('LidosPage', () => {
  let component: LidosPage;
  let fixture: ComponentFixture<LidosPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(LidosPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
