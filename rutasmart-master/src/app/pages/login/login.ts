import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../api.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  email = '';
  password = '';
  message = '';

  constructor(private readonly router: Router, private readonly api: ApiService) {}

  goDashboard(): void {
    this.api.login(this.email || 'juan@utp.edu.pe', this.password || 'demo').subscribe({
      next: () => this.router.navigateByUrl('/dashboard'),
      error: () => {
        this.message = 'No se pudo conectar al backend. Revisa que rutasmartservice-master esté ejecutándose.';
      }
    });
  }
}
