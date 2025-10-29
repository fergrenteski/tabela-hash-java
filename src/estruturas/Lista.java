package estruturas;

/**
 * Interface Lista genérica
 * Define os métodos básicos de uma lista
 */
public interface Lista {
    
    /**
     * Adiciona um elemento no final da lista
     */
    void adicionar(String elemento);
    
    /**
     * Obtém um elemento pelo índice
     */
    String obter(int indice);
    
    /**
     * Remove um elemento pelo índice
     */
    void remover(int indice);
    
    /**
     * Retorna o tamanho da lista
     */
    int tamanho();
    
    /**
     * Verifica se a lista está vazia
     */
    boolean estaVazia();
    
    /**
     * Limpa todos os elementos da lista
     */
    void limpar();
}
