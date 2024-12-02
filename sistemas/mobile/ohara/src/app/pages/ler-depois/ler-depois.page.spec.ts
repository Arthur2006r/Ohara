import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LerDepoisPage } from './ler-depois.page';

describe('LerDepoisPage', () => {
  let component: LerDepoisPage;
  let fixture: ComponentFixture<LerDepoisPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(LerDepoisPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
