import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MangasCurtidosPage } from './mangas-curtidos.page';

describe('MangasCurtidosPage', () => {
  let component: MangasCurtidosPage;
  let fixture: ComponentFixture<MangasCurtidosPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(MangasCurtidosPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
