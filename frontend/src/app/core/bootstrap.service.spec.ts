/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { BootstrapService } from './bootstrap.service';

describe('BootstrapService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BootstrapService]
    });
  });

  it('should ...', inject([BootstrapService], (service: BootstrapService) => {
    expect(service).toBeTruthy();
  }));
});
