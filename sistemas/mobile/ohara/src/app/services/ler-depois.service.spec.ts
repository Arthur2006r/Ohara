import { TestBed } from '@angular/core/testing';

import { LerDepoisService } from './ler-depois.service';

describe('LerDepoisService', () => {
  let service: LerDepoisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LerDepoisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
