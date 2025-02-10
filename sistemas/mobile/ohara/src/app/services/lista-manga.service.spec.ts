import { TestBed } from '@angular/core/testing';

import { ListaMangaService } from './lista-manga.service';

describe('ListaMangaService', () => {
  let service: ListaMangaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListaMangaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
