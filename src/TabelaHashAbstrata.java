import estruturas.No;

/**
 * Classe abstrata para Tabela Hash
 * Define a estrutura básica e comportamento comum das tabelas hash
 * Classes concretas devem implementar apenas a função hash específica
 */
public abstract class TabelaHashAbstrata {
    protected No[] tabela;
    protected int tamanho;
    protected int quantidadeElementos;
    protected int colisoes;
    protected int[] colisoesRorPosicao; // Array para contar colisões por posição
    
    /**
     * Construtor da tabela hash
     * @param tamanho Tamanho da tabela
     */
    public TabelaHashAbstrata(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new No[tamanho];
        this.quantidadeElementos = 0;
        this.colisoes = 0;
        this.colisoesRorPosicao = new int[tamanho];
    }
    
    /**
     * Método abstrato que deve ser implementado pelas classes concretas
     * Define a função hash específica
     */
    protected abstract int funcaoHash(String chave);
    
    /**
     * Retorna o nome da função hash (para relatórios)
     */
    public abstract String getNomeFuncaoHash();
    
    /**
     * Insere um par chave-valor na tabela
     * Usa encadeamento para tratar colisões
     */
    public void inserir(String chave, int valor) {
        int indice = funcaoHash(chave);
        No novoNo = new No(chave, valor);
        
        // Se a posição está vazia, insere diretamente
        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
            quantidadeElementos++;
        } else {
            // Colisão! Usa encadeamento
            colisoes++;
            colisoesRorPosicao[indice]++;
            
            No atual = tabela[indice];
            
            // Verifica se a chave já existe (atualiza valor)
            while (atual != null) {
                if (atual.getChave().equals(chave)) {
                    atual.setValor(valor);
                    return;
                }
                if (atual.getProximo() == null) {
                    break;
                }
                atual = atual.getProximo();
            }
            
            // Adiciona no final da lista encadeada
            atual.setProximo(novoNo);
            quantidadeElementos++;
        }
    }
    
    /**
     * Busca um valor pela chave
     * @return O valor se encontrado, -1 caso contrário
     */
    public int buscar(String chave) {
        int indice = funcaoHash(chave);
        No atual = tabela[indice];
        
        // Percorre a lista encadeada na posição
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                return atual.getValor();
            }
            atual = atual.getProximo();
        }
        
        return -1; // Não encontrado
    }
    
    /**
     * Remove um elemento pela chave
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String chave) {
        int indice = funcaoHash(chave);
        No atual = tabela[indice];
        No anterior = null;
        
        // Percorre a lista encadeada
        while (atual != null) {
            if (atual.getChave().equals(chave)) {
                if (anterior == null) {
                    // Remover o primeiro nó
                    tabela[indice] = atual.getProximo();
                } else {
                    // Remover nó do meio/fim
                    anterior.setProximo(atual.getProximo());
                }
                
                quantidadeElementos--;
                return true;
            }
            
            anterior = atual;
            atual = atual.getProximo();
        }
        
        return false;
    }
    
    /**
     * Retorna estatísticas da tabela
     */
    public Estatisticas obterEstatisticas() {
        int posicoesOcupadas = 0;
        int tamanhoMaximoCadeia = 0;
        
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                posicoesOcupadas++;
                
                int tamanho = 0;
                No atual = tabela[i];
                while (atual != null) {
                    tamanho++;
                    atual = atual.getProximo();
                }
                
                if (tamanho > tamanhoMaximoCadeia) {
                    tamanhoMaximoCadeia = tamanho;
                }
            }
        }
        
        int posicoesVazias = tamanho - posicoesOcupadas;
        double taxaOcupacao = (posicoesOcupadas * 100.0) / tamanho;
        
        return new Estatisticas(quantidadeElementos, colisoes, posicoesOcupadas, 
                                posicoesVazias, tamanhoMaximoCadeia, taxaOcupacao);
    }
    
    /**
     * Retorna a distribuição das chaves (quantidade por posição)
     */
    public int[] obterDistribuicao() {
        int[] distribuicao = new int[tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            int count = 0;
            No atual = tabela[i];
            while (atual != null) {
                count++;
                atual = atual.getProximo();
            }
            distribuicao[i] = count;
        }
        
        return distribuicao;
    }
    
    /**
     * Retorna array com colisões por posição
     */
    public int[] getColisoesRorPosicao() {
        return colisoesRorPosicao;
    }
    
    // Getters
    public int getColisoes() {
        return colisoes;
    }
    
    public int getQuantidadeElementos() {
        return quantidadeElementos;
    }
    
    public int getTamanho() {
        return tamanho;
    }
}
