import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailColoniaComponent } from './detail-colonia.component';

describe('DetailColoniaComponent', () => {
  let component: DetailColoniaComponent;
  let fixture: ComponentFixture<DetailColoniaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailColoniaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailColoniaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
