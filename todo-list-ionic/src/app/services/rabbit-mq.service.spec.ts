import { TestBed } from '@angular/core/testing';

import { RabbitMQService } from './rabbit-mq.service';

describe('RabbitMQService', () => {
  let service: RabbitMQService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RabbitMQService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
