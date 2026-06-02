# rutasmartservice-master

Backend Spring Boot de RutaSmart.

## Ejecutar

```bat
cd C:\Users\Teseo OS\Desktop\rutasmart\rutasmartservice-master
mvnw.cmd spring-boot:run
```

URL base: `http://localhost:8080/api`

## Endpoints usados por rutasmart-master

- `POST /api/auth/login`
- `POST /api/incidents`
- `GET /api/health`

## Supabase

Configura estas variables para usar PostgreSQL de Supabase:

```bat
set SPRING_DATASOURCE_URL=jdbc:postgresql://db.TU_PROJECT.supabase.co:5432/postgres?sslmode=require
set SPRING_DATASOURCE_USERNAME=postgres
set SPRING_DATASOURCE_PASSWORD=TU_PASSWORD_DE_SUPABASE
set SPRING_DATASOURCE_DRIVER=org.postgresql.Driver
```

Sin esas variables usa H2 local para pruebas.
