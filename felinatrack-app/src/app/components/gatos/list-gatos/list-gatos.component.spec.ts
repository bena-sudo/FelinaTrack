import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListGatosComponent } from './list-gatos.component';

describe('ListGatosComponent', () => {
  let component: ListGatosComponent;
  let fixture: ComponentFixture<ListGatosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListGatosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListGatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
