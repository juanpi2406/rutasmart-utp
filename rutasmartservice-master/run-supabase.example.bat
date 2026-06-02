@echo off
REM Copia este archivo como run-supabase.bat y completa tus credenciales reales.
set "SPRING_DATASOURCE_URL=jdbc:postgresql://TU_POOLER_HOST:6543/postgres?sslmode=require&prepareThreshold=0"
set "SPRING_DATASOURCE_USERNAME=postgres.TU_PROJECT_REF"
set "SPRING_DATASOURCE_PASSWORD=TU_PASSWORD"
set "SPRING_DATASOURCE_DRIVER=org.postgresql.Driver"
call mvnw.cmd spring-boot:run
