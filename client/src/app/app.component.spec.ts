import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
describe('App', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({ declarations: [AppComponent]});
  });
  it ('should work', () => {
    expect('sample').toEqual('sample');
  });
});
