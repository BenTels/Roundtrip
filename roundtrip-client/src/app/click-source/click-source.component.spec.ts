import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClickSourceComponent } from './click-source.component';

describe('ClickSourceComponent', () => {
  let component: ClickSourceComponent;
  let fixture: ComponentFixture<ClickSourceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClickSourceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClickSourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
