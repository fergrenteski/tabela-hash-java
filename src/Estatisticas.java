/**
 * Classe para armazenar estatísticas da tabela hash
 */
public class Estatisticas {
    private int elementos;
    private int colisoes;
    private int posicoesOcupadas;
    private int posicoesVazias;
    private int maiorCadeia;
    private double taxaOcupacao;
    private long tempoInsercao; // em nanosegundos
    private long tempoBusca; // em nanosegundos
    
    public Estatisticas(int elementos, int colisoes, int posicoesOcupadas, 
                       int posicoesVazias, int maiorCadeia, double taxaOcupacao) {
        this.elementos = elementos;
        this.colisoes = colisoes;
        this.posicoesOcupadas = posicoesOcupadas;
        this.posicoesVazias = posicoesVazias;
        this.maiorCadeia = maiorCadeia;
        this.taxaOcupacao = taxaOcupacao;
        this.tempoInsercao = 0;
        this.tempoBusca = 0;
    }
    
    // Getters
    public int getElementos() {
        return elementos;
    }
    
    public int getColisoes() {
        return colisoes;
    }
    
    public int getPosicoesOcupadas() {
        return posicoesOcupadas;
    }
    
    public int getPosicoesVazias() {
        return posicoesVazias;
    }
    
    public int getMaiorCadeia() {
        return maiorCadeia;
    }
    
    public double getTaxaOcupacao() {
        return taxaOcupacao;
    }
    
    public long getTempoInsercao() {
        return tempoInsercao;
    }
    
    public void setTempoInsercao(long tempoInsercao) {
        this.tempoInsercao = tempoInsercao;
    }
    
    public long getTempoBusca() {
        return tempoBusca;
    }
    
    public void setTempoBusca(long tempoBusca) {
        this.tempoBusca = tempoBusca;
    }
}
