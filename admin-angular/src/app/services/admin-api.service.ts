import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventModel } from '../models/event.model';

@Injectable({ providedIn: 'root' })
export class AdminApiService {
  private api = 'http://localhost:8080/api';
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> { return this.http.post(`${this.api}/auth/login`, { email, password }); }
  listEvents(): Observable<EventModel[]> { return this.http.get<EventModel[]>(`${this.api}/events`); }
  getEvent(id: string): Observable<EventModel> { return this.http.get<EventModel>(`${this.api}/events/${id}`); }
  createEvent(event: EventModel): Observable<EventModel> { return this.http.post<EventModel>(`${this.api}/events`, event); }
  updateEvent(id: string, event: EventModel): Observable<EventModel> { return this.http.put<EventModel>(`${this.api}/events/${id}`, event); }
  deleteEvent(id: number): Observable<void> { return this.http.delete<void>(`${this.api}/events/${id}`); }
}
