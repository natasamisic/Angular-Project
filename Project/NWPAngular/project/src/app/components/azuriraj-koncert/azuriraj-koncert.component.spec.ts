import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AzurirajKoncertComponent } from './azuriraj-koncert.component';

describe('AzurirajKoncertComponent', () => {
  let component: AzurirajKoncertComponent;
  let fixture: ComponentFixture<AzurirajKoncertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AzurirajKoncertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AzurirajKoncertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
