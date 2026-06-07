import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent implements OnInit {

  nombre = '';
  ruta = '';
  paradero = '';

  proximoBus = '';
  estadoBus = '';
  distancia = '';
  tiempoEstimado = '';

  proximasSalidas: string[] = [];

constructor(
    private api: ApiService,
    private router: Router
) {}


ngOnInit(): void {

  // Nombre obtenido desde el login
  this.nombre =
    localStorage.getItem('rutasmart.nombre') || '';

  this.api.obtenerDashboard()
    .subscribe({
      next: (data) => {

        // NO sobreescribimos el nombre del login
        // this.nombre = data.nombre;

        this.ruta = data.ruta;
        this.paradero = data.paradero;

        this.proximoBus = data.proximoBus;
        this.estadoBus = data.estadoBus;
        this.distancia = data.distancia;
        this.tiempoEstimado = data.tiempoEstimado;

        this.proximasSalidas = data.proximasSalidas;

      },
      error: (err) => {
        console.error('Error al cargar dashboard:', err);
      }
    });

}

logout(): void {

    localStorage.removeItem('rutasmart.email');

    localStorage.removeItem('rutasmart.nombre');

    localStorage.removeItem('rutasmart.rol');

    this.router.navigate(['/login']);

}





}
