import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KoncertItemComponent } from './koncert-item.component';

describe('KoncertItemComponent', () => {
  let component: KoncertItemComponent;
  let fixture: ComponentFixture<KoncertItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KoncertItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KoncertItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
