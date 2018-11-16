import { TestBed } from '@angular/core/testing';

import { NotAuthenticatedGuardService } from './not-authenticated-guard.service';

describe('NotAuthenticatedGuardService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NotAuthenticatedGuardService = TestBed.get(NotAuthenticatedGuardService);
    expect(service).toBeTruthy();
  });
});
