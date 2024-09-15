import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VisualizarMangaPage } from './visualizar-manga.page';

describe('VisualizarMangaPage', () => {
  let component: VisualizarMangaPage;
  let fixture: ComponentFixture<VisualizarMangaPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizarMangaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
