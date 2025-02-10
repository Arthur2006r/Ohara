import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddListaPage } from './add-lista.page';

describe('AddListaPage', () => {
  let component: AddListaPage;
  let fixture: ComponentFixture<AddListaPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(AddListaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
