import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import estruturas.ArrayList;
import estruturas.Lista;

/**
 * Classe principal para testar e comparar as tabelas hash
 * 
 * TDE 03 - Resolução de Problemas Estruturados em Computação
 * Prof.ª Marina de Lara
 * 
 * Implementação de Tabelas Hash com Diferentes Funções Hash
 */
public class Principal {
    
    private static final int TAMANHO_TABELA = 32;
    private static final String ARQUIVO_NOMES = "female_names.txt";
    
    public static void main(String[] args) {
        imprimirCabecalho();
        
        // 1. Ler arquivo com nomes
        System.out.println("\n[1] LENDO ARQUIVO DE NOMES...");
        Lista nomes = lerArquivo(ARQUIVO_NOMES);
        
        if (nomes == null || nomes.estaVazia()) {
            System.out.println("❌ Erro: Não foi possível ler o arquivo de nomes!");
            return;
        }
        
        System.out.println("✓ Arquivo lido com sucesso!");
        System.out.println("✓ Total de nomes: " + nomes.tamanho());
        
        // 2. Criar as duas tabelas hash
        System.out.println("\n[2] CRIANDO TABELAS HASH...");
        TabelaHashAbstrata tabela1 = new TabelaHashSomaSimples(TAMANHO_TABELA);
        TabelaHashAbstrata tabela2 = new TabelaHashPolinomial(TAMANHO_TABELA);
        System.out.println("✓ Tabela 1: " + tabela1.getNomeFuncaoHash());
        System.out.println("✓ Tabela 2: " + tabela2.getNomeFuncaoHash());
        System.out.println("✓ Tamanho das tabelas: " + TAMANHO_TABELA);
        
        // 3. Testar Tabela 1
        System.out.println("\n[3] TESTANDO TABELA 1 - " + tabela1.getNomeFuncaoHash());
        System.out.println("=".repeat(70));
        Estatisticas stats1 = testarTabela(tabela1, nomes);
        
        // 4. Testar Tabela 2
        System.out.println("\n[4] TESTANDO TABELA 2 - " + tabela2.getNomeFuncaoHash());
        System.out.println("=".repeat(70));
        Estatisticas stats2 = testarTabela(tabela2, nomes);
        
        // 5. Gerar relatório comparativo
        System.out.println("\n[5] RELATÓRIO COMPARATIVO FINAL");
        System.out.println("=".repeat(70));
        gerarRelatorioComparativo(tabela1, stats1, tabela2, stats2);
        
        imprimirRodape();
    }
    
    /**
     * Lê o arquivo de nomes e retorna uma lista
     */
    private static Lista lerArquivo(String nomeArquivo) {
        Lista nomes = new ArrayList();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    nomes.adicionar(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erro ao ler arquivo: " + e.getMessage());
            return null;
        }
        
        return nomes;
    }
    
    /**
     * Testa uma tabela hash com inserção e busca
     */
    private static Estatisticas testarTabela(TabelaHashAbstrata tabela, Lista nomes) {
        // Teste de INSERÇÃO
        System.out.println("\n→ Inserindo " + nomes.tamanho() + " nomes...");
        long inicioInsercao = System.nanoTime();
        
        for (int i = 0; i < nomes.tamanho(); i++) {
            tabela.inserir(nomes.obter(i), i + 1);
        }
        
        long fimInsercao = System.nanoTime();
        long tempoInsercao = fimInsercao - inicioInsercao;
        
        System.out.println("✓ Inserção concluída!");
        System.out.printf("✓ Tempo de inserção: %.6f segundos%n", tempoInsercao / 1_000_000_000.0);
        
        // Teste de BUSCA
        System.out.println("\n→ Testando busca em 1000 nomes aleatórios...");
        long inicioBusca = System.nanoTime();
        
        int buscasEncontradas = 0;
        int seed = 42;
        for (int i = 0; i < 1000; i++) {
            // Gerador de números pseudo-aleatórios simples (Linear Congruential Generator)
            seed = (seed * 1103515245 + 12345) & 0x7fffffff;
            int indiceAleatorio = seed % nomes.tamanho();
            String nomeAleatorio = nomes.obter(indiceAleatorio);
            if (tabela.buscar(nomeAleatorio) != -1) {
                buscasEncontradas++;
            }
        }
        
        long fimBusca = System.nanoTime();
        long tempoBusca = fimBusca - inicioBusca;
        
        System.out.println("✓ Busca concluída!");
        System.out.printf("✓ Tempo de busca: %.6f segundos%n", tempoBusca / 1_000_000_000.0);
        System.out.println("✓ Nomes encontrados: " + buscasEncontradas + "/1000");
        
        // Obter estatísticas
        Estatisticas stats = tabela.obterEstatisticas();
        stats.setTempoInsercao(tempoInsercao);
        stats.setTempoBusca(tempoBusca);
        
        // Exibir estatísticas básicas
        System.out.println("\n📊 ESTATÍSTICAS:");
        System.out.println("   • Elementos inseridos: " + stats.getElementos());
        System.out.println("   • Colisões totais: " + stats.getColisoes());
        System.out.println("   • Posições ocupadas: " + stats.getPosicoesOcupadas() + "/" + TAMANHO_TABELA);
        System.out.println("   • Posições vazias: " + stats.getPosicoesVazias() + "/" + TAMANHO_TABELA);
        System.out.println("   • Maior cadeia: " + stats.getMaiorCadeia() + " elementos");
        System.out.printf("   • Taxa de ocupação: %.1f%%%n", stats.getTaxaOcupacao());
        
        return stats;
    }
    
    /**
     * Gera relatório comparativo entre as duas tabelas
     */
    private static void gerarRelatorioComparativo(TabelaHashAbstrata tabela1, Estatisticas stats1,
                                                   TabelaHashAbstrata tabela2, Estatisticas stats2) {
        
        System.out.println("\n📊 COMPARAÇÃO DE MÉTRICAS");
        System.out.println("-".repeat(70));
        System.out.printf("%-35s | %-15s | %-15s%n", "Métrica", "Tabela 1", "Tabela 2");
        System.out.println("-".repeat(70));
        System.out.printf("%-35s | %-15s | %-15s%n", "Função Hash", 
            tabela1.getNomeFuncaoHash(), tabela2.getNomeFuncaoHash());
        System.out.printf("%-35s | %-15d | %-15d%n", "Elementos inseridos",
            stats1.getElementos(), stats2.getElementos());
        System.out.printf("%-35s | %-15d | %-15d%n", "Colisões totais",
            stats1.getColisoes(), stats2.getColisoes());
        System.out.printf("%-35s | %-15d | %-15d%n", "Posições ocupadas",
            stats1.getPosicoesOcupadas(), stats2.getPosicoesOcupadas());
        System.out.printf("%-35s | %-15d | %-15d%n", "Maior cadeia",
            stats1.getMaiorCadeia(), stats2.getMaiorCadeia());
        System.out.printf("%-35s | %.6f ms   | %.6f ms%n", "Tempo de inserção",
            stats1.getTempoInsercao() / 1_000_000.0, stats2.getTempoInsercao() / 1_000_000.0);
        System.out.printf("%-35s | %.6f ms   | %.6f ms%n", "Tempo de busca",
            stats1.getTempoBusca() / 1_000_000.0, stats2.getTempoBusca() / 1_000_000.0);
        System.out.println("-".repeat(70));
        
        // Distribuição das chaves
        System.out.println("\n📊 DISTRIBUIÇÃO DAS CHAVES POR POSIÇÃO");
        System.out.println("-".repeat(70));
        System.out.printf("%-10s | %-25s | %-25s%n", "Posição", 
            "Tabela 1 (Qtd)", "Tabela 2 (Qtd)");
        System.out.println("-".repeat(70));
        
        int[] dist1 = tabela1.obterDistribuicao();
        int[] dist2 = tabela2.obterDistribuicao();
        
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            System.out.printf("%-10d | %-25d | %-25d%n", i, dist1[i], dist2[i]);
        }
        System.out.println("-".repeat(70));
        
        // Colisões por posição
        System.out.println("\n📊 COLISÕES POR POSIÇÃO (CLUSTERIZAÇÃO)");
        System.out.println("-".repeat(70));
        System.out.printf("%-10s | %-25s | %-25s%n", "Posição", 
            "Tabela 1 (Colisões)", "Tabela 2 (Colisões)");
        System.out.println("-".repeat(70));
        
        int[] colisoes1 = tabela1.getColisoesRorPosicao();
        int[] colisoes2 = tabela2.getColisoesRorPosicao();
        
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            if (colisoes1[i] > 0 || colisoes2[i] > 0) {
                System.out.printf("%-10d | %-25d | %-25d%n", i, colisoes1[i], colisoes2[i]);
            }
        }
        System.out.println("-".repeat(70));
        
        // Conclusão
        System.out.println("\n✅ CONCLUSÃO:");
        
        if (stats1.getColisoes() < stats2.getColisoes()) {
            System.out.println("→ A Tabela 1 (" + tabela1.getNomeFuncaoHash() + ") teve MENOS colisões");
        } else if (stats2.getColisoes() < stats1.getColisoes()) {
            System.out.println("→ A Tabela 2 (" + tabela2.getNomeFuncaoHash() + ") teve MENOS colisões");
        } else {
            System.out.println("→ Ambas as tabelas tiveram o mesmo número de colisões");
        }
        
        if (stats1.getTempoInsercao() < stats2.getTempoInsercao()) {
            System.out.println("→ A Tabela 1 foi mais RÁPIDA na inserção");
        } else {
            System.out.println("→ A Tabela 2 foi mais RÁPIDA na inserção");
        }
        
        if (stats1.getTempoBusca() < stats2.getTempoBusca()) {
            System.out.println("→ A Tabela 1 foi mais RÁPIDA na busca");
        } else {
            System.out.println("→ A Tabela 2 foi mais RÁPIDA na busca");
        }
        
        if (stats1.getMaiorCadeia() < stats2.getMaiorCadeia()) {
            System.out.println("→ A Tabela 1 teve melhor distribuição (menor cadeia máxima)");
        } else if (stats2.getMaiorCadeia() < stats1.getMaiorCadeia()) {
            System.out.println("→ A Tabela 2 teve melhor distribuição (menor cadeia máxima)");
        }
    }
    
    private static void imprimirCabecalho() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  IMPLEMENTAÇÃO DE TABELAS HASH COM DIFERENTES FUNÇÕES HASH");
        System.out.println("=".repeat(70));
        System.out.println("  TDE 03 - Resolução de Problemas Estruturados em Computação");
        System.out.println("  Prof.ª Marina de Lara");
        System.out.println("  Pontifícia Universidade Católica do Paraná");
        System.out.println("=".repeat(70));
    }
    
    private static void imprimirRodape() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  FIM DA EXECUÇÃO");
        System.out.println("=".repeat(70) + "\n");
    }
}
