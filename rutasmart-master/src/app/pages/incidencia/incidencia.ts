import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ApiService } from '../../api.service';

@Component({
  selector: 'app-incidencia',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './incidencia.html',
  styleUrl: './incidencia.css'
})
export class IncidenciaComponent {
  type = 'Problema con el Bus';
  description = '';
  message = '';

  constructor(private readonly api: ApiService) {}

  sendReport(): void {
    if (!this.description.trim()) {
      this.message = 'Escribe una descripción para enviar el reporte.';
      return;
    }

    this.api.createIncident(this.type, this.description).subscribe({
      next: () => {
        this.description = '';
        this.message = 'Reporte enviado correctamente.';
      },
      error: () => {
        this.message = 'No se pudo enviar. Revisa que rutasmartservice-master esté ejecutándose.';
      }
    });
  }
}
