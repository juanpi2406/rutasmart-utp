import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { ApiService, DashboardData, Programacion }
from '../../api.service';

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
  mensajeReserva = '';
  rol = '';

  esAdmin = false;
  esAlumno = false;
  esChofer = false;

  proximasSalidas: string[] = [];
  programaciones: Programacion[] = [];
    latitud = 0;

  longitud = 0;

  velocidad = 0;

  busX = 650;
  busY = 120;

  totalAlumnos = 125;
totalBuses = 5;
totalRutas = 8;
totalReservas = 42;
paraderoTop = 'Villa El Salvador';
paraderoMenosUsado = 'Atocongo';
totalIncidencias = 3;
viajesHoy = 18;

placaBus = 'ABC-123';

rutaChofer = 'Lima Sur';

estadoRuta = 'SIN INICIAR';

viajesHoyChofer = 3;

pasajerosActuales = 18;

capacidadBus = 40;

proximaSalida = '07:00';

reservasConfirmadas = 18;

paraderoActual = 'Villa El Salvador';

incidenciasPendientes = 1;


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



    this.api.obtenerProgramaciones()
  .subscribe({

    next: (data) => {

      this.programaciones = data;

    },

    error: (err) => {

      console.error(
        'Error al cargar programaciones:',
        err
      );

    }

  });



 /* this.api.obtenerUbicacion(1)
    .subscribe({

      next: (data) => {

        this.latitud = data.latitud;

        this.longitud = data.longitud;

        this.velocidad = data.velocidad;

        // Conversión temporal para mover el bus
      this.busX = 450 + ((data.longitud + 77) * 500);
      this.busY = 300 - ((data.latitud + 12.3) * 1000);

      },

      error: (err) => {

        console.error(
            'Error ubicación:',
            err
        );

      }

    });*/


    this.rol =
    localStorage.getItem('rutasmart.rol') || '';

  this.esAdmin = this.rol === 'ADMIN';
  this.esAlumno = this.rol === 'ALUMNO';
  this.esChofer = this.rol === 'CHOFER';

}

logout(): void {

    localStorage.removeItem('rutasmart.email');

    localStorage.removeItem('rutasmart.nombre');

    localStorage.removeItem('rutasmart.rol');

    this.router.navigate(['/login']);

}

reservar(idViaje: number): void {

  const idAlumno = Number(
    localStorage.getItem('rutasmart.idAlumno')
  );

  if (!idAlumno) {

    this.mensajeReserva =
      'No se encontró el alumno autenticado';

    return;
  }

  this.api.reservar({

    idAlumno: idAlumno,

    idViaje: idViaje

  }).subscribe({

    next: (resp: any) => {

      this.mensajeReserva =
        resp.mensaje;

    },

    error: (err) => {

      console.error(err);

      this.mensajeReserva =
        'Error al registrar reserva';

    }

  });

}


iniciarRuta(){

  this.estadoRuta = 'EN RUTA';

}

finalizarRuta(){

  this.estadoRuta = 'FINALIZADA';

}

actualizarUbicacion(){

  alert('Ubicación actualizada');

}


}
