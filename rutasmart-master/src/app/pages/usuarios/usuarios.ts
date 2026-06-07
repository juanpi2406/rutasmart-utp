import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../api.service';
import { Router } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
     RouterLink
  ],
  templateUrl: './usuarios.html',
  // Cambiamos a styleUrls (con la 's' al final) y lo pasamos dentro de corchetes
  styleUrls: ['./usuarios.css']
})
export class UsuariosComponent {

  constructor(
  private api: ApiService,
  private router: Router
) {}

  codigo = '';

  nombres = '';

  apellidos = '';

  correo = '';

  password = '';

  rol = 'ALUMNO';

  facultad = '';

  escuela = '';

  ciclo = 1;

  licencia = '';

  fechaVencimiento = '';
  usuarioGuardado = false;


nuevoUsuario(){

  this.usuarioGuardado = false;

  this.codigo = '';
  this.nombres = '';
  this.apellidos = '';
  this.correo = '';
  this.password = '';

}



  guardarUsuario(){

    this.api.crearUsuario({

      codigo: this.codigo,

      nombres: this.nombres,

      apellidos: this.apellidos,

      correo: this.correo,

      password: this.password,

      telefono: '',

      rol: this.rol,

      facultad: this.facultad,

      escuela: this.escuela,

      ciclo: this.ciclo

    }).subscribe({

      next:(r)=>{

        alert('Usuario creado correctamente');
        this.usuarioGuardado = true;

        this.router.navigateByUrl(
          '/dashboard'
        );

        console.log(r);

      },

      error:(e)=>{

        console.error(e);

        alert(
          'Error al crear usuario'
        );

      }

    });

  }

  volverDashboard(){

  this.router.navigateByUrl(
    '/dashboard'
  );

}

}
