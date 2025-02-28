import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListActuacionesComponent } from './list-actuaciones.component';

describe('ListActuacionesComponent', () => {
  let component: ListActuacionesComponent;
  let fixture: ComponentFixture<ListActuacionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListActuacionesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListActuacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
