import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService, RegisterPayload } from '../../api.service';

type LoginMode = 'login' | 'register' | 'forgot';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  mode: LoginMode = 'login';
  message = '';
  loading = false;

  email = '';
  password = '';
  remember = false;

  register: RegisterPayload & { confirmPassword: string } = {
    name: '',
    email: '',
    code: '',
    phone: '',
    assignedRoute: '',
    password: '',
    confirmPassword: ''
  };

  recoveryEmail = '';

  constructor(private readonly router: Router, private readonly api: ApiService) {}

  setMode(mode: LoginMode): void {
    this.mode = mode;
    this.message = '';
  }

  goDashboard(): void {
    if (!this.isValidEmail(this.email)) {
      this.message = 'Ingresa un correo válido.';
      return;
    }
    if (this.password.length < 6) {
      this.message = 'La contraseña debe tener al menos 6 caracteres.';
      return;
    }

    this.loading = true;
    this.api.login(this.email, this.password).subscribe({
   next: (response) => {

  this.loading = false;

  if (response.mensaje === 'Login exitoso') {

  localStorage.setItem(
    'rutasmart.idUsuario',
    String(response.idUsuario)
  );

  localStorage.setItem(
    'rutasmart.idAlumno',
    String(response.idAlumno)
  );

    localStorage.setItem('rutasmart.email', this.email);

    localStorage.setItem(
      'rutasmart.nombre',
      response.nombre
    );

    localStorage.setItem(
      'rutasmart.rol',
      response.rol
    );

    this.router.navigateByUrl('/dashboard');

  }
  else {

    this.message = response.mensaje;

  }

},
      error: () => {
        this.loading = false;
        this.message = 'No se pudo iniciar sesión. Revisa el backend o tus credenciales.';
      }
    });
  }

  submitRegister(): void {
    const error = this.validateRegister();
    if (error) {
      this.message = error;
      return;
    }

    this.loading = true;
    const { confirmPassword, ...payload } = this.register;
    this.api.registerStudent(payload).subscribe({
      next: (response) => {
        this.loading = false;
        this.message = response.message;
        this.email = payload.email;
        this.password = payload.password;
        localStorage.setItem('rutasmart.email', payload.email);
        this.setMode('login');
      },
      error: (error) => {
        this.loading = false;
        this.message = error?.error?.message || 'No se pudo registrar el estudiante.';
      }
    });
  }

  submitForgot(): void {
    if (!this.isValidEmail(this.recoveryEmail)) {
      this.message = 'Ingresa un correo válido para recuperar la contraseña.';
      return;
    }

    this.loading = true;
    this.api.forgotPassword(this.recoveryEmail).subscribe({
      next: (response) => {
        this.loading = false;
        this.message = response.message;
      },
      error: () => {
        this.loading = false;
        this.message = 'No se pudo procesar la recuperación.';
      }
    });
  }

  connectSocial(provider: 'google' | 'microsoft'): void {
    this.loading = true;
    this.api.socialAuth(provider).subscribe({
      next: (response) => {
        this.loading = false;
        if (response.url) {
          window.location.href = response.url;
          return;
        }
        this.message = response.message;
      },
      error: () => {
        this.loading = false;
        this.message = `No se pudo conectar con ${provider}. Verifica Supabase Auth.`;
      }
    });
  }

  private validateRegister(): string {
    if (this.register.name.trim().length < 3) return 'Ingresa nombres y apellidos válidos.';
    if (!this.isValidEmail(this.register.email)) return 'Ingresa un correo válido.';
    if (!/^U\d{8}$/i.test(this.register.code)) return 'El código debe tener formato U########.';
    if (!/^\d{9}$/.test(this.register.phone)) return 'El teléfono debe tener 9 dígitos.';
    if (!this.register.assignedRoute.trim()) return 'Selecciona o escribe una ruta asignada.';
    if (this.register.password.length < 6) return 'La contraseña debe tener al menos 6 caracteres.';
    if (this.register.password !== this.register.confirmPassword) return 'Las contraseñas no coinciden.';
    return '';
  }

  private isValidEmail(value: string): boolean {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value.trim());
  }
}
