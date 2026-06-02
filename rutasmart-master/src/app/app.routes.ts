import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login';
import { DashboardComponent } from './pages/dashboard/dashboard';
import { PerfilComponent } from './pages/perfil/perfil';
import { IncidenciaComponent } from './pages/incidencia/incidencia';
import { ReportesComponent } from './pages/reportes/reportes';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'incidencia', component: IncidenciaComponent },
  { path: 'reportes', component: ReportesComponent },
  { path: '**', redirectTo: 'login' }
];
