import { TestBed } from '@angular/core/testing';
import { AdminEventsListComponent } from './admin-events-list.component';

describe('AdminEventsListComponent', () => {
  it('deve criar componente com sucesso', async () => {
    await TestBed.configureTestingModule({ imports: [AdminEventsListComponent] }).compileComponents();
    const fixture = TestBed.createComponent(AdminEventsListComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });
});
