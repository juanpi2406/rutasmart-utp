import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login';
import { DashboardComponent } from './pages/dashboard/dashboard';
import { PerfilComponent } from './pages/perfil/perfil';
import { IncidenciaComponent } from './pages/incidencia/incidencia';
import { ReportesComponent } from './pages/reportes/reportes';
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
  path: 'dashboard',
  component: DashboardComponent,
  canActivate: [authGuard]
},
  {
  path: 'perfil',
  component: PerfilComponent,
  canActivate: [authGuard]
},
  {
  path: 'incidencia',
  component: IncidenciaComponent,
  canActivate: [authGuard]
},
  {
  path: 'reportes',
  component: ReportesComponent,
  canActivate: [authGuard]
},
  { path: '**', redirectTo: 'dashboard' }
];
