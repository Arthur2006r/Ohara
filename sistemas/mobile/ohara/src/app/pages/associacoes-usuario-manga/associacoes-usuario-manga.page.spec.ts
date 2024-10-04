import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AssociacoesUsuarioMangaPage } from './associacoes-usuario-manga.page';

describe('AssociacoesUsuarioMangaPage', () => {
  let component: AssociacoesUsuarioMangaPage;
  let fixture: ComponentFixture<AssociacoesUsuarioMangaPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociacoesUsuarioMangaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
