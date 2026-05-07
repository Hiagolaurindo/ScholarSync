import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AdminApiService } from '../../services/admin-api.service';
import { EventModel } from '../../models/event.model';

// Event table for admins with edit/delete actions.
@Component({
  selector: 'app-admin-events-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  template: `<h2>Eventos</h2>
  <a routerLink="/admin/events/new">Novo evento</a>
  <table border="1"><tr><th>ID</th><th>Titulo</th><th>Data</th><th>Local</th><th>Capacidade</th><th>Acoes</th></tr>
  <tr *ngFor="let e of events"><td>{{e.id}}</td><td>{{e.title}}</td><td>{{e.date}}</td><td>{{e.location}}</td><td>{{e.capacity}}</td>
  <td><a [routerLink]="['/admin/events', e.id, 'edit']">Editar</a> <button (click)="remove(e.id!)">Excluir</button></td></tr></table>`
})
export class AdminEventsListComponent implements OnInit {
  events: EventModel[] = [];
  constructor(private api: AdminApiService, private router: Router) {}
  ngOnInit(): void { this.load(); }
  load(): void { this.api.listEvents().subscribe((r) => (this.events = r)); }
  remove(id: number): void { if (confirm('Confirma exclusao?')) this.api.deleteEvent(id).subscribe(() => this.load()); }
}
