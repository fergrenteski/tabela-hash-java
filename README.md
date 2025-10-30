# 📊 Tabela Hash - Comparação de Funções Hash

Implementação didática de tabelas hash em Java, comparando duas diferentes funções hash: **Soma Simples** e **Polinomial**. Este projeto demonstra como diferentes funções de hash afetam o desempenho e a distribuição de dados em estruturas hash.

## 🎯 Objetivo

Implementar e comparar duas funções hash distintas para demonstrar:
- Como diferentes algoritmos de hash afetam a distribuição de dados
- O impacto das colisões no desempenho
- Técnicas de tratamento de colisões por encadeamento
- Métricas de desempenho (tempo de inserção e busca)

## 🏗️ Estrutura do Projeto

```
hash/
├── src/
│   ├── Principal.java                 # Classe principal com testes
│   ├── TabelaHashAbstrata.java        # Classe abstrata base
│   ├── TabelaHashSomaSimples.java     # Função hash 1: Soma Simples
│   ├── TabelaHashPolinomial.java      # Função hash 2: Polinomial
│   ├── Estatisticas.java              # Classe para métricas
│   └── estruturas/
│       ├── ArrayList.java             # Lista dinâmica
│       ├── Lista.java                 # Interface de lista
│       └── No.java                    # Nó para encadeamento
├── female_names.txt                   # Dataset de teste
├── compilar_executar.sh               # Script para Linux/Mac
└── compilar_executar.bat              # Script para Windows
```

## 🔑 Funções Hash Implementadas

### 1️⃣ Soma Simples (ASCII)
```java
protected int funcaoHash(String chave) {
    int soma = 0;
    for (int i = 0; i < chave.length(); i++) {
        soma += chave.charAt(i);
    }
    return Math.abs(soma % tamanho);
}
```
**Características:**
- ✅ Simples e rápida
- ❌ Mais propensa a colisões
- ❌ Anagramas produzem o mesmo hash

### 2️⃣ Polinomial (Rolling Hash - Base 31)
```java
protected int funcaoHash(String chave) {
    long hashValor = 0;
    int base = 31; // Número primo como base
    
    for (int i = 0; i < chave.length(); i++) {
        hashValor = (hashValor * base + chave.charAt(i));
    }
    
    return (int) (Math.abs(hashValor) % tamanho);
}
```
**Características:**
- ✅ Melhor distribuição
- ✅ Menos colisões
- ✅ Leva em conta a ordem dos caracteres
- ⚖️ Ligeiramente mais complexa

## 🚀 Como Executar

### Opção 1: Scripts Automatizados

**Linux/Mac:**
```bash
chmod +x compilar_executar.sh
./compilar_executar.sh
```

**Windows:**
```batch
compilar_executar.bat
```

### Opção 2: Comandos Manuais

```bash
# Compilar
javac -d bin src/*.java src/estruturas/*.java

# Copiar arquivo de dados
cp female_names.txt bin/

# Executar
cd bin
java Principal
```

## 📋 Requisitos

- ☕ Java 8 ou superior
- 📦 JDK instalado e configurado
- 📄 Arquivo `female_names.txt` no diretório raiz

## 📊 Saída do Programa

O programa executa os seguintes passos:

1. **Leitura do Arquivo**: Carrega nomes do arquivo de texto
2. **Criação das Tabelas**: Inicializa ambas as tabelas hash
3. **Teste da Tabela 1**: Insere e busca dados usando Soma Simples
4. **Teste da Tabela 2**: Insere e busca dados usando Polinomial
5. **Relatório Comparativo**: Exibe métricas detalhadas

### Exemplo de Saída:

```
======================================================================
  IMPLEMENTAÇÃO DE TABELAS HASH COM DIFERENTES FUNÇÕES HASH
======================================================================

[1] LENDO ARQUIVO DE NOMES...
✓ Arquivo lido com sucesso!
✓ Total de nomes: 5001

[2] CRIANDO TABELAS HASH...
✓ Tabela 1: Soma Simples (ASCII)
✓ Tabela 2: Polinomial (Base 31)
✓ Tamanho das tabelas: 32

[3] TESTANDO TABELA 1 - Soma Simples (ASCII)
======================================================================

→ Inserindo 5001 nomes...
✓ Inserção concluída!
✓ Tempo de inserção: 0.025431 segundos

→ Testando busca em 1000 nomes aleatórios...
✓ Busca concluída!
✓ Tempo de busca: 0.003214 segundos
✓ Nomes encontrados: 1000/1000

📊 ESTATÍSTICAS:
   • Elementos inseridos: 5001
   • Colisões totais: 4969
   • Posições ocupadas: 32/32
   • Posições vazias: 0/32
   • Maior cadeia: 282 elementos
   • Taxa de ocupação: 100.0%

[5] RELATÓRIO COMPARATIVO FINAL
======================================================================

📊 COMPARAÇÃO DE MÉTRICAS
----------------------------------------------------------------------
Métrica                             | Tabela 1        | Tabela 2       
----------------------------------------------------------------------
Função Hash                         | Soma Simples    | Polinomial     
Elementos inseridos                 | 5001            | 5001           
Colisões totais                     | 4969            | 4969           
Posições ocupadas                   | 32              | 32             
Maior cadeia                        | 282             | 197            
Tempo de inserção                   | 25.431000 ms    | 23.567000 ms   
Tempo de busca                      | 3.214000 ms     | 2.891000 ms    
----------------------------------------------------------------------

✅ CONCLUSÃO:
→ A Tabela 2 (Polinomial (Base 31)) teve melhor distribuição
→ A Tabela 2 foi mais RÁPIDA na inserção
→ A Tabela 2 foi mais RÁPIDA na busca
```

## 📈 Métricas Coletadas

O programa coleta e compara:

- ⏱️ **Tempo de inserção**: Tempo para inserir todos os elementos
- 🔍 **Tempo de busca**: Tempo para 1000 buscas aleatórias
- 💥 **Colisões totais**: Número de colisões durante inserções
- 📍 **Posições ocupadas**: Quantas posições da tabela foram usadas
- 🔗 **Maior cadeia**: Tamanho da maior lista encadeada
- 📊 **Distribuição**: Quantidade de elementos por posição
- 🎯 **Taxa de ocupação**: Percentual da tabela utilizada

## 🎓 Conceitos Demonstrados

### Tratamento de Colisões
- **Encadeamento (Chaining)**: Cada posição da tabela contém uma lista encadeada
- Quando ocorre colisão, o novo elemento é adicionado ao final da lista

### Análise de Complexidade
- **Inserção**: O(1) no caso médio, O(n) no pior caso
- **Busca**: O(1) no caso médio, O(n) no pior caso
- **Espaço**: O(n)

## 🔬 Resultados Esperados

Em geral, a função **Polinomial** apresenta:
- ✅ Melhor distribuição dos dados
- ✅ Menos clusterização (menor cadeia máxima)
- ✅ Melhor desempenho em buscas
- ✅ Menos colisões em posições específicas

A função **Soma Simples** pode ter:
- ⚠️ Mais colisões devido a anagramas
- ⚠️ Distribuição menos uniforme
- ⚠️ Cadeias mais longas em certas posições

## 🛠️ Possíveis Extensões

- [ ] Implementar mais funções hash (multiplicação, divisão, etc.)
- [ ] Adicionar redimensionamento dinâmico
- [ ] Implementar endereçamento aberto
- [ ] Adicionar testes com diferentes tipos de dados
- [ ] Visualização gráfica da distribuição
- [ ] Testes de performance com datasets maiores

## 📚 Referências

- **Estruturas de Dados**: Conceitos fundamentais de tabelas hash
- **Função Hash Polinomial**: Baseada na implementação do Java String.hashCode()
- **Encadeamento**: Técnica clássica de tratamento de colisões

## 👨‍💻 Autor

Implementação didática para estudo de estruturas de dados e algoritmos de hash.

## 📝 Licença

Este projeto é de uso educacional e livre para estudo e modificação.

---

⭐ **Dica**: Execute o programa várias vezes e observe como os tempos podem variar, mas a distribuição de colisões tende a ser consistente para cada função hash!