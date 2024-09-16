import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KoncertViewComponent } from './koncert-view.component';

describe('KoncertViewComponent', () => {
  let component: KoncertViewComponent;
  let fixture: ComponentFixture<KoncertViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KoncertViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KoncertViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
