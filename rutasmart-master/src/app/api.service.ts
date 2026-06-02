import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = 'http://localhost:8080/api';

  constructor(private readonly http: HttpClient) {}

  login(email: string, password: string): Observable<{ message: string; role: string }> {
    return this.http.post<{ message: string; role: string }>(`${this.baseUrl}/auth/login`, { email, password });
  }

  createIncident(type: string, description: string, photoName = ''): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/incidents`, { type, description, photoName });
  }
}
