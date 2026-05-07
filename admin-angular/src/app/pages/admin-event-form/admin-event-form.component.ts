import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminApiService } from '../../services/admin-api.service';
import { EventModel } from '../../models/event.model';

// Reused for create and edit; checks route param id to decide action.
@Component({
  selector: 'app-admin-event-form',
  standalone: true,
  imports: [FormsModule],
  template: `<h2>{{id ? 'Editar' : 'Novo'}} evento</h2>
  <input [(ngModel)]="event.title" placeholder="title" />
  <input [(ngModel)]="event.description" placeholder="description" />
  <input [(ngModel)]="event.date" placeholder="YYYY-MM-DD" />
  <input [(ngModel)]="event.location" placeholder="location" />
  <input [(ngModel)]="event.capacity" type="number" placeholder="capacity" />
  <button (click)="save()">Salvar</button>`
})
export class AdminEventFormComponent implements OnInit {
  id: string | null = null;
  event: EventModel = { title: '', description: '', date: '2026-06-10', location: '', capacity: 1 };
  constructor(private route: ActivatedRoute, private api: AdminApiService, private router: Router) {}
  ngOnInit(): void { this.id = this.route.snapshot.paramMap.get('id'); if (this.id) this.api.getEvent(this.id).subscribe((e) => (this.event = e)); }
  save(): void {
    const req = this.id ? this.api.updateEvent(this.id, this.event) : this.api.createEvent(this.event);
    req.subscribe(() => this.router.navigate(['/admin/events']));
  }
}
