import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListColoniasComponent } from './list-colonias.component';

describe('ListColoniasComponent', () => {
  let component: ListColoniasComponent;
  let fixture: ComponentFixture<ListColoniasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListColoniasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListColoniasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
