import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ApiService, StudentProfile } from '../../api.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './perfil.html',
  styleUrl: './perfil.css'
})
export class PerfilComponent implements OnInit {
  editing = false;
  loading = false;
  message = '';
  sessionEmail = localStorage.getItem('rutasmart.email') || '';

  profile: StudentProfile = {
    name: 'Cargando...',
    email: '',
    code: '',
    phone: '',
    assignedRoute: ''
  };

  constructor(private readonly api: ApiService) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.loading = true;
    this.api.getProfile(this.sessionEmail || undefined).subscribe({
      next: (profile) => {
        this.profile = profile;
        this.sessionEmail = profile.email;
        this.loading = false;
        if (profile.email) {
          localStorage.setItem('rutasmart.email', profile.email);
        }
      },
      error: (error) => {
        this.loading = false;
        this.message = error?.status === 404
          ? 'No hay un perfil registrado para este correo.'
          : 'No se pudo cargar el perfil.';
        this.profile = {
          name: '',
          email: this.sessionEmail,
          code: '',
          phone: '',
          assignedRoute: ''
        };
      }
    });
  }

  saveProfile(): void {
    this.loading = true;
    this.api.updateProfile(this.profile).subscribe({
      next: (profile) => {
        this.profile = profile;
        this.sessionEmail = profile.email;
        localStorage.setItem('rutasmart.email', profile.email);
        this.editing = false;
        this.loading = false;
        this.message = 'Perfil actualizado correctamente.';
      },
      error: () => {
        this.loading = false;
        this.message = 'No se pudo actualizar el perfil.';
      }
    });
  }

  startEdit(): void {
    this.editing = true;
    this.message = '';
  }

  cancelEdit(): void {
    this.editing = false;
    this.loadProfile();
  }
}
