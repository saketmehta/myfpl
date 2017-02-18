/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { FixtureService } from './fixture.service';

describe('FixtureService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FixtureService]
    });
  });

  it('should ...', inject([FixtureService], (service: FixtureService) => {
    expect(service).toBeTruthy();
  }));
});
