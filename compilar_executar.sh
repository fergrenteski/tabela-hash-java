#!/bin/bash

# Script para compilar e executar o projeto de Tabela Hash
# Sistema: Linux/macOS

echo "========================================"
echo "  COMPILANDO PROJETO TABELA HASH"
echo "========================================"

# Limpar diretório bin se existir
if [ -d "bin" ]; then
    echo "→ Limpando diretório bin..."
    rm -rf bin/*.class
else
    echo "→ Criando diretório bin..."
    mkdir -p bin
fi

# Compilar todos os arquivos Java
echo "→ Compilando arquivos Java..."
javac -d bin src/estruturas/*.java src/*.java

# Verificar se a compilação foi bem-sucedida
if [ $? -eq 0 ]; then
    echo "✓ Compilação concluída com sucesso!"
    echo ""
    echo "========================================"
    echo "  EXECUTANDO PROGRAMA"
    echo "========================================"
    echo ""
    
    # Copiar arquivo de nomes para o diretório bin
    if [ -f "female_names.txt" ]; then
        cp female_names.txt bin/
        echo "✓ Arquivo de nomes copiado para bin/"
    else
        echo "⚠ Aviso: arquivo female_names.txt não encontrado!"
    fi
    
    # Executar o programa
    cd bin
    java Principal
    cd ..
    
else
    echo "❌ Erro na compilação!"
    exit 1
fi

echo ""
echo "========================================"
echo "  EXECUÇÃO CONCLUÍDA"
echo "========================================"
