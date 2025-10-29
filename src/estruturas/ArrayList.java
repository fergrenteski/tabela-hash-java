package estruturas;

/**
 * Implementação de ArrayList (Lista Dinâmica)
 * Lista que cresce dinamicamente conforme necessário
 * Implementada do zero sem usar java.util
 */
public class ArrayList implements Lista {
    private String[] elementos;
    private int tamanho;
    private static final int CAPACIDADE_INICIAL = 10;
    
    /**
     * Construtor padrão
     */
    public ArrayList() {
        this.elementos = new String[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }
    
    /**
     * Construtor com capacidade inicial específica
     */
    public ArrayList(int capacidadeInicial) {
        this.elementos = new String[capacidadeInicial];
        this.tamanho = 0;
    }
    
    @Override
    public void adicionar(String elemento) {
        // Verifica se precisa aumentar a capacidade
        if (tamanho == elementos.length) {
            aumentarCapacidade();
        }
        elementos[tamanho] = elemento;
        tamanho++;
    }
    
    @Override
    public String obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        return elementos[indice];
    }
    
    @Override
    public void remover(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        
        // Desloca os elementos para a esquerda
        for (int i = indice; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        
        elementos[tamanho - 1] = null;
        tamanho--;
    }
    
    @Override
    public int tamanho() {
        return tamanho;
    }
    
    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }
    
    @Override
    public void limpar() {
        for (int i = 0; i < tamanho; i++) {
            elementos[i] = null;
        }
        tamanho = 0;
    }
    
    /**
     * Aumenta a capacidade do array interno
     */
    private void aumentarCapacidade() {
        int novaCapacidade = elementos.length * 2;
        String[] novoArray = new String[novaCapacidade];
        
        // Copia os elementos para o novo array
        for (int i = 0; i < elementos.length; i++) {
            novoArray[i] = elementos[i];
        }
        
        elementos = novoArray;
    }
    
    /**
     * Retorna a capacidade atual do array interno
     */
    public int capacidade() {
        return elementos.length;
    }
}
