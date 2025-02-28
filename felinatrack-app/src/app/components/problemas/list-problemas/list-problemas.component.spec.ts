import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProblemasComponent } from './list-problemas.component';

describe('ListProblemasComponent', () => {
  let component: ListProblemasComponent;
  let fixture: ComponentFixture<ListProblemasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListProblemasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListProblemasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
