import estruturas.No;

/**
 * Tabela Hash com Função Hash 1: Soma Simples
 * Soma os valores ASCII dos caracteres
 * - Simples e rápida
 * - Mais propensa a colisões
 */
public class TabelaHashSomaSimples extends TabelaHashAbstrata {
    
    public TabelaHashSomaSimples(int tamanho) {
        super(tamanho);
    }
    
    /**
     * FUNÇÃO HASH 1: Soma Simples
     * Soma os valores ASCII de todos os caracteres da chave
     */
    @Override
    protected int funcaoHash(String chave) {
        int soma = 0;
        for (int i = 0; i < chave.length(); i++) {
            soma += chave.charAt(i);
        }
        return Math.abs(soma % tamanho);
    }
    
    @Override
    public String getNomeFuncaoHash() {
        return "Soma Simples (ASCII)";
    }
}
