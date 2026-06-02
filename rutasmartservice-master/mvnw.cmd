@echo off
setlocal
set "MAVEN_HOME=%~dp0..\.tools\apache-maven-3.9.9"
set "MAVEN_CMD=%MAVEN_HOME%\bin\mvn.cmd"
if not exist "%MAVEN_CMD%" (
  echo Maven local no encontrado en %MAVEN_HOME%.
  echo Revisa que exista la carpeta ..\.tools\apache-maven-3.9.9
  exit /b 1
)
call "%MAVEN_CMD%" %*
