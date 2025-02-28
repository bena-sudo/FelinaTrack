import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailGatoComponent } from './detail-gato.component';

describe('DetailGatoComponent', () => {
  let component: DetailGatoComponent;
  let fixture: ComponentFixture<DetailGatoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailGatoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailGatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
