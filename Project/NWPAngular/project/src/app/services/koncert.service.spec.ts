import { TestBed } from '@angular/core/testing';

import { KoncertService } from './koncert.service';

describe('KoncertService', () => {
  let service: KoncertService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KoncertService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
