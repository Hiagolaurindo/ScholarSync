import { Routes } from '@angular/router';
import { AdminLoginComponent } from './pages/admin-login/admin-login.component';
import { AdminEventsListComponent } from './pages/admin-events-list/admin-events-list.component';
import { AdminEventFormComponent } from './pages/admin-event-form/admin-event-form.component';

export const routes: Routes = [
  { path: 'admin/login', component: AdminLoginComponent },
  { path: 'admin/events', component: AdminEventsListComponent },
  { path: 'admin/events/new', component: AdminEventFormComponent },
  { path: 'admin/events/:id/edit', component: AdminEventFormComponent },
  { path: '', redirectTo: 'admin/login', pathMatch: 'full' }
];
