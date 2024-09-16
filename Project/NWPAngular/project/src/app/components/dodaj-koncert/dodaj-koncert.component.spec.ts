import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajKoncertComponent } from './dodaj-koncert.component';

describe('DodajKoncertComponent', () => {
  let component: DodajKoncertComponent;
  let fixture: ComponentFixture<DodajKoncertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DodajKoncertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajKoncertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
