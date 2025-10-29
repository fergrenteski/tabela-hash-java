import estruturas.No;

/**
 * Tabela Hash com Função Hash 2: Polinomial
 * Usa uma base polinomial para melhor distribuição
 * - Mais complexa
 * - Menos colisões, melhor distribuição
 */
public class TabelaHashPolinomial extends TabelaHashAbstrata {
    
    public TabelaHashPolinomial(int tamanho) {
        super(tamanho);
    }
    
    /**
     * FUNÇÃO HASH 2: Polinomial (Rolling Hash)
     * Usa uma base prima (31) para calcular hash polinomial
     * Oferece melhor distribuição que soma simples
     */
    @Override
    protected int funcaoHash(String chave) {
        long hashValor = 0;
        int base = 31; // Número primo como base
        
        for (int i = 0; i < chave.length(); i++) {
            hashValor = (hashValor * base + chave.charAt(i));
        }
        
        return (int) (Math.abs(hashValor) % tamanho);
    }
    
    @Override
    public String getNomeFuncaoHash() {
        return "Polinomial (Base 31)";
    }
}
