import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ApiService, IncidentRecord } from '../../api.service';

@Component({
  selector: 'app-incidencia',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './incidencia.html',
  styleUrl: './incidencia.css'
})
export class IncidenciaComponent implements OnInit {
  type = 'Problema con el Bus';
  description = '';
  message = '';
  incidents: IncidentRecord[] = [];

  constructor(private readonly api: ApiService) {}

  ngOnInit(): void {
    this.loadIncidents();
  }

  loadIncidents(): void {
    this.api.getIncidents().subscribe({
      next: (incidents) => {
        this.incidents = [...incidents].sort((a, b) => (b.id ?? 0) - (a.id ?? 0));
      },
      error: () => {
        this.message = 'No se pudo cargar la lista de incidencias.';
      }
    });
  }

  sendReport(): void {
    if (!this.description.trim()) {
      this.message = 'Escribe una descripciÃ³n para enviar el reporte.';
      return;
    }

    this.api.createIncident(this.type, this.description).subscribe({
      next: (incident) => {
        this.description = '';
        this.message = 'Reporte enviado correctamente.';
        this.incidents = [incident, ...this.incidents.filter((item) => item.id !== incident.id)];
      },
      error: () => {
        this.message = 'No se pudo enviar. Revisa que rutasmartservice-master estÃ© ejecutÃ¡ndose.';
      }
    });
  }

  trackByIncident(_: number, incident: IncidentRecord): number | string {
    return incident.id ?? incident.time ?? incident.type;
  }

  statusClass(status: string): string {
    const normalized = status.toLowerCase();
    if (normalized.includes('revisi')) {
      return 'status-red';
    }
    if (normalized.includes('resuelto') || normalized.includes('complet')) {
      return 'status-green';
    }
    return 'status-pending';
  }

  iconKind(type: string): 'roadwork' | 'sign' | 'traffic' {
    const normalized = type.toLowerCase();
    if (normalized.includes('paradero')) {
      return 'sign';
    }
    if (normalized.includes('trafic')) {
      return 'traffic';
    }
    return 'roadwork';
  }
}
