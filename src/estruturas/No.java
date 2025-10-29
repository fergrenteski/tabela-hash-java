package estruturas;
/**
 * Nó para armazenar chave-valor em uma lista encadeada
 * Usado para tratamento de colisões por encadeamento
 */
public class No {
    private String chave;
    private int valor;
    private No proximo;
    
    public No(String chave, int valor) {
        this.chave = chave;
        this.valor = valor;
        this.proximo = null;
    }
    
    // Getters e Setters
    public String getChave() {
        return chave;
    }
    
    public void setChave(String chave) {
        this.chave = chave;
    }
    
    public int getValor() {
        return valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public No getProximo() {
        return proximo;
    }
    
    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
