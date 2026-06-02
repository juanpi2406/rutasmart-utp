import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface AuthMessageResponse {
  message: string;
}

export interface SocialAuthResponse {
  provider: string;
  url: string;
  message: string;
}

export interface RegisterPayload {
  name: string;
  email: string;
  code: string;
  phone: string;
  assignedRoute: string;
  password: string;
}

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = 'http://localhost:8080/api';

  constructor(private readonly http: HttpClient) {}

  login(email: string, password: string): Observable<{ message: string; role: string }> {
    return this.http.post<{ message: string; role: string }>(`${this.baseUrl}/auth/login`, { email, password });
  }

  registerStudent(payload: RegisterPayload): Observable<AuthMessageResponse> {
    return this.http.post<AuthMessageResponse>(`${this.baseUrl}/auth/register`, payload);
  }

  forgotPassword(email: string): Observable<AuthMessageResponse> {
    return this.http.post<AuthMessageResponse>(`${this.baseUrl}/auth/forgot-password`, { email });
  }

  socialAuth(provider: 'google' | 'microsoft'): Observable<SocialAuthResponse> {
    return this.http.get<SocialAuthResponse>(`${this.baseUrl}/auth/social/${provider}`);
  }

  createIncident(type: string, description: string, photoName = ''): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/incidents`, { type, description, photoName });
  }
}
