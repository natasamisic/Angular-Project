import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KoncertiComponent } from './koncerti.component';

describe('KoncertiComponent', () => {
  let component: KoncertiComponent;
  let fixture: ComponentFixture<KoncertiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KoncertiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KoncertiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
