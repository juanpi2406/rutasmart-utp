# RutaSmart App

Sistema de seguimiento de transporte universitario en tiempo real.

## Proyectos

- `rutasmart-master`: frontend Angular.
- `rutasmartservice-master`: backend Spring Boot conectado a PostgreSQL/Supabase.

## Ejecutar backend

```bat
cd rutasmartservice-master
copy run-supabase.example.bat run-supabase.bat
```

Edita `run-supabase.bat` con tus credenciales reales de Supabase y ejecuta:

```bat
run-supabase.bat
```

## Ejecutar frontend

```bat
cd rutasmart-master
npm install
npm start
```

Frontend: `http://localhost:4200`
Backend: `http://localhost:8080`
