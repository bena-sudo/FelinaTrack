import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListResponsablesComponent } from './list-responsables.component';

describe('ListResponsablesComponent', () => {
  let component: ListResponsablesComponent;
  let fixture: ComponentFixture<ListResponsablesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListResponsablesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListResponsablesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
