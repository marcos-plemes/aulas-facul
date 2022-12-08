import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Genetica {
    
    private final Random geradorDeNumeros = new Random();
    
    private final Double mutacao;
    
    private final Integer pesoMaximo;
    
    private final Integer numeroDeCromossomos;
    
    private final Integer numeroDeGeracoes;
    
    private List<List<Integer>> populacao;
    
    private List<BigDecimal> historicoDeFitnes;
    
    private List<Item> itens;
    
    public Genetica(
            final Integer pesoMaximo,
            final Integer numeroDeCromossomos,
            final Integer numeroDeGeracoes,
            final List<Item> itens) {
        this.pesoMaximo = pesoMaximo;
        this.numeroDeCromossomos = numeroDeCromossomos;
        this.numeroDeGeracoes = numeroDeGeracoes;
        this.mutacao = 0.05;
        this.itens = itens;
        this.gerarPopulacaoInicial();
    }
    
    public Genetica(
            final Integer pesoMaximo,
            final Integer numeroDeCromossomos,
            final Integer numeroDeGeracoes,
            final List<Item> itens,
            final Double mutacao) {
        this.pesoMaximo = pesoMaximo;
        this.numeroDeCromossomos = numeroDeCromossomos;
        this.numeroDeGeracoes = numeroDeGeracoes;
        this.mutacao = mutacao;
        this.gerarPopulacaoInicial();
        this.itens = itens;
    }
    
    public void gerarPopulacaoInicial() {
        this.populacao = new ArrayList<>();
        for (int i = 0; i < numeroDeCromossomos; i++) {
            this.populacao.add(this.retornarIndividoAleatorio());
        }
        
    }
    
    private List<Integer> retornarIndividoAleatorio() {
        final List<Integer> individuo = new ArrayList<>();
        
        for (int i = 0; i < this.itens.size(); i++) {
            individuo.add(this.geradorDeNumeros.nextInt(2));
        }
        
        return individuo;
    }
    
    public BigDecimal mediaDeFitness(final List<Item> itens, final List<List<Integer>> populacao, final Integer pesoMaximo) {
        
        BigDecimal soma = BigDecimal.ZERO;
        for (int i = 0; i < populacao.size(); i++) {
            final BigDecimal fitness = this.fitness(populacao.get(i), pesoMaximo, itens);
            if (BigDecimal.ZERO.compareTo(fitness) < 0) {
                soma = soma.add(fitness);
            }
        }
        
        return soma.divide(BigDecimal.valueOf(populacao.size()));
    }
    
    private BigDecimal fitness(final List<Integer> individuo, final Integer pesoMaximo, final List<Item> itens) {
        Integer pesoTotal = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
        
        for (int i = 0; i < individuo.size(); i++) {
            pesoTotal += individuo.get(i) * itens.get(i).getPeso();
            valorTotal = valorTotal.add(itens.get(i).getValor().multiply(BigDecimal.valueOf(individuo.get(i))));
        }
        
        if ((pesoMaximo - pesoTotal) < 0) {
            return BigDecimal.valueOf(-1);
        }
        return valorTotal;
    }
    
    public List<List<Integer>> envolve() {
        
        final List<Pai> pais = new ArrayList<>();
        for (int i = 0; i < this.populacao.size(); i++) {
            final BigDecimal fitness = this.fitness(this.populacao.get(i), this.pesoMaximo, this.itens);
            if (BigDecimal.ZERO.compareTo(fitness) < 0) {
                pais.add(new Pai(fitness, this.populacao.get(i)));
            }
        }
        Collections.sort(pais);
        
        final List<List<Integer>> filhos = new ArrayList<>();
        for (int i = 0; i < this.numeroDeCromossomos; i++) {
            BigDecimal fitnessTotal = this.fitnessTotal(pais);
            final Integer indiceDoPai = this.selecionarRoleta(pais, -1, fitnessTotal);
            fitnessTotal = fitnessTotal.subtract(pais.get(indiceDoPai).getFitness());
            final Integer indiceDaMae = this.selecionarRoleta(pais, indiceDoPai, fitnessTotal);
            filhos.add(new ArrayList<>());
            for (int j = 0; j < this.itens.size(); j++) {
                if (j < this.itens.size() / 2) {
                    filhos.get(i).add(pais.get(indiceDoPai).getIndividuo().get(j));
                } else {
                    filhos.get(i).add(pais.get(indiceDaMae).getIndividuo().get(j));
                }
            }
        }
        
        for (final List<Integer> individuo : filhos) {
            if (this.mutacao > this.geradorDeNumeros.nextDouble()) {
                final Integer indiceDaMutacao = this.geradorDeNumeros.nextInt(individuo.size());
                if (individuo.get(indiceDaMutacao).equals(1)) {
                    individuo.set(indiceDaMutacao, 0);
                } else {
                    individuo.set(indiceDaMutacao, 1);
                }
            }
        }
        
        return filhos;
        
    }
    
    private BigDecimal fitnessTotal(final List<Pai> pais) {
        return pais.stream().map(Pai::getFitness).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    private Integer selecionarRoleta(final List<Pai> pais, final Integer indiceAIgnorar, final BigDecimal fitnessTotal) {
        
        Integer indice = null;
        do {
            indice = this.sortear(pais, indiceAIgnorar, fitnessTotal);
        } while (Objects.isNull(indice));
        
        return indice;
        
    }
    
    private Integer sortear(final List<Pai> pais, final Integer indice, final BigDecimal fitnessTotal) {
        
        final BigDecimal valorSorteado = BigDecimal.valueOf(this.geradorDeNumeros.nextDouble());
        BigDecimal acumulado = BigDecimal.ZERO;
        
        for (int i = 0; i < pais.size(); i++) {
            if (i == indice) {
                continue;
            }
            acumulado = acumulado.add(pais.get(i).getFitness());
            final BigDecimal roleta = acumulado.divide(fitnessTotal, 17, RoundingMode.HALF_UP);
            if (roleta.compareTo(valorSorteado) >= 0) {
                return i;
            }
        }
        
        return null;
        
    }
    
    public void build() {
        for (int i = 0; i < this.numeroDeGeracoes; i++) {
            this.populacao = this.envolve();
        }
        this.imprimirSolucao();
    }

    private void imprimirSolucao() {
        System.out.println(this.populacao.get(0));
        System.out.println();
        Integer pesoTotal = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(int i = 0; i<this.itens.size(); i++) {
            if(this.populacao.get(0).get(i).equals(1)) {
                System.out.println(this.itens.get(i).getNome());
                pesoTotal += this.itens.get(i).getPeso();
                valorTotal = valorTotal.add(this.itens.get(i).getValor());
            }
        }

        System.out.println();
        System.out.println("Peso total:" + pesoTotal);
        System.out.println("Valor total:" + valorTotal);


    }

    public List<List<Integer>> getPopulacao() {
        return this.populacao;
    }
    
    public void setPopulacao(final List<List<Integer>> populacao) {
        this.populacao = populacao;
    }
    
    public List<BigDecimal> getHistoricoDeFitnes() {
        return this.historicoDeFitnes;
    }
    
    public void setHistoricoDeFitnes(final List<BigDecimal> historicoDeFitnes) {
        this.historicoDeFitnes = historicoDeFitnes;
    }
    
    public Random getGeradorDeNumeros() {
        return this.geradorDeNumeros;
    }
    
    public Double getMutacao() {
        return this.mutacao;
    }
    
    public Integer getPesoMaximo() {
        return this.pesoMaximo;
    }
    
    public Integer getNumeroDeCromossomos() {
        return this.numeroDeCromossomos;
    }
    
    public Integer getNumeroDeGeracoes() {
        return this.numeroDeGeracoes;
    }
    
    public List<Item> getItens() {
        return this.itens;
    }
    
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

}
