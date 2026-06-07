import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface DashboardData {
  nombre: string;
  ruta: string;
  paradero: string;
  proximoBus: string;
  estadoBus: string;
  distancia: string;
  tiempoEstimado: string;
  proximasSalidas: string[];
}

export interface Programacion {
  ruta: string;
  horaSalida: string;
  horaLlegadaEstimada: string;
  diasOperacion: string;
  estado: boolean;
}

export interface UbicacionBus {

  idUbicacion: number;

  idViaje: number;

  latitud: number;

  longitud: number;

  velocidad: number;

  fechaHora: string;

}
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

export interface StudentProfile {
  id?: number;
  name: string;
  email: string;
  code: string;
  phone: string;
  assignedRoute: string;
}

export interface IncidentRecord {
  id?: number;
  type: string;
  description: string;
  routeName: string;
  time: string;
  status: string;
  photoName?: string;
}

export interface LoginResponse {

  idUsuario: number;

  idAlumno: number;

  nombre: string;

  rol: string;

  mensaje: string;

}

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = 'http://localhost:8081/api';

  constructor(private readonly http: HttpClient) {}

login(correo: string, password: string): Observable<LoginResponse> {
  return this.http.post<LoginResponse>(
    `${this.baseUrl}/auth/login`,
    {
      correo: correo,
      password: password
    }
  );
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

  getProfile(email?: string): Observable<StudentProfile> {
    const params = email ? new HttpParams().set('email', email) : undefined;
    return this.http.get<StudentProfile>(`${this.baseUrl}/student/profile`, { params });
  }

  updateProfile(profile: StudentProfile): Observable<StudentProfile> {
    const params = profile.email ? new HttpParams().set('email', profile.email) : undefined;
    return this.http.put<StudentProfile>(`${this.baseUrl}/student/profile`, profile, { params });
  }

  getIncidents(): Observable<IncidentRecord[]> {
    return this.http.get<IncidentRecord[]>(`${this.baseUrl}/incidents`);
  }

  createIncident(type: string, description: string, photoName = ''): Observable<IncidentRecord> {
    return this.http.post<IncidentRecord>(`${this.baseUrl}/incidents`, { type, description, photoName });
  }


obtenerRutas() {
  return this.http.get(`${this.baseUrl}/rutas`);
}

obtenerViajes() {
  return this.http.get(`${this.baseUrl}/viajes`);
}

obtenerReservas() {
  return this.http.get(`${this.baseUrl}/reservas`);
}

obtenerDashboard(): Observable<DashboardData> {
  return this.http.get<DashboardData>(
    'http://localhost:8081/api/dashboard'
  );
}

obtenerProgramaciones(): Observable<Programacion[]> {

  return this.http.get<Programacion[]>(
    `${this.baseUrl}/programaciones`
  );

}

obtenerUbicacion(
    idViaje: number
): Observable<UbicacionBus> {

    return this.http.get<UbicacionBus>(
        `${this.baseUrl}/ubicaciones/${idViaje}`
    );

}


  reservar(data:any){

    return this.http.post(
      `${this.baseUrl}/reservas`,
      data
    );

  }

  crearUsuario(data:any){

  return this.http.post(
    `${this.baseUrl}/usuarios`,
    data
  );

}
}
