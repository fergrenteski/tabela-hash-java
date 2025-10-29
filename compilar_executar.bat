@echo off
REM Script para compilar e executar o projeto de Tabela Hash
REM Sistema: Windows

echo ========================================
echo   COMPILANDO PROJETO TABELA HASH
echo ========================================

REM Limpar diretório bin se existir
if exist "bin" (
    echo -^> Limpando diretorio bin...
    del /Q bin\*.class 2>nul
) else (
    echo -^> Criando diretorio bin...
    mkdir bin
)

REM Compilar todos os arquivos Java
echo -^> Compilando arquivos Java...
javac -d bin src\estruturas\*.java src\*.java

REM Verificar se a compilação foi bem-sucedida
if %ERRORLEVEL% EQU 0 (
    echo. 
    echo Compilacao concluida com sucesso!
    echo.
    echo ========================================
    echo   EXECUTANDO PROGRAMA
    echo ========================================
    echo.
    
    REM Copiar arquivo de nomes para o diretório bin
    if exist "female_names.txt" (
        copy female_names.txt bin\ >nul
        echo Arquivo de nomes copiado para bin\
    ) else (
        echo Aviso: arquivo female_names.txt nao encontrado!
    )
    
    REM Executar o programa
    cd bin
    java Principal
    cd ..
    
) else (
    echo Erro na compilacao!
    pause
    exit /b 1
)

echo.
echo ========================================
echo   EXECUCAO CONCLUIDA
echo ========================================
pause
