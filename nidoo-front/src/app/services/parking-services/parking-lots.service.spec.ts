import { TestBed } from '@angular/core/testing';

import { ParkingLotsService } from './parking-lots.service';

describe('ParkingLotsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ParkingLotsService = TestBed.get(ParkingLotsService);
    expect(service).toBeTruthy();
  });
});
