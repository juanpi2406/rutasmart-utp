import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../api.service';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-reservas',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './reservas.html',
  styleUrl: './reservas.css'
})
export class ReservasComponent implements OnInit {

  viajes:any[] = [];
  paraderos:any[] = [];
  paraderosSeleccionados:{
  [idViaje:number]:number
} = {};

  mensaje = '';

  constructor(
    private api:ApiService
  ){}

  ngOnInit(): void {

    this.cargarViajes();
    this.cargarParaderos();

  }

  cargarParaderos(){

  this.api.obtenerParaderos()
    .subscribe({

      next:(data:any)=>{

        this.paraderos = data;

        console.log(data);

      },

      error:(err)=>{

        console.error(err);

      }

    });

}


 cargarViajes(){

  this.api.obtenerViajesDisponibles()
    .subscribe({

      next:(data:any)=>{

        const hoy = new Date()
          .toISOString()
          .split('T')[0];

        this.viajes = data.filter(
          (v:any)=>
            v.fechaViaje === hoy
        );

      },   // <-- ESTA COMA FALTA

      error:()=>{

        this.mensaje =
          'No se pudieron cargar los viajes';

      }

    });

}



  reservar(idViaje:number){

  const idAlumno =
    Number(
      localStorage.getItem(
        'rutasmart.idAlumno'
      )
    );

  const idParadero =
    this.paraderosSeleccionados[idViaje];

  if(!idParadero){

    this.mensaje =
      'Seleccione un paradero';

    return;

  }

  this.api.reservarViaje(
    idAlumno,
    idViaje,
    idParadero
  )
  .subscribe({

    next:(response:any)=>{

      this.mensaje =
        response.mensaje;

      this.cargarViajes();

    },

    error:()=>{

      this.mensaje =
        'Error al reservar';

    }

  });



}




}
