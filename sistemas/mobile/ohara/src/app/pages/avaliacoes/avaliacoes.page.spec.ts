import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AvaliacoesPage } from './avaliacoes.page';

describe('AvaliacoesPage', () => {
  let component: AvaliacoesPage;
  let fixture: ComponentFixture<AvaliacoesPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(AvaliacoesPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
