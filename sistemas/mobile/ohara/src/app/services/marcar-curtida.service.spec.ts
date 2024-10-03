import { TestBed } from '@angular/core/testing';

import { MarcarCurtidaService } from './marcar-curtida.service';

describe('MarcarCurtidaService', () => {
  let service: MarcarCurtidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MarcarCurtidaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
