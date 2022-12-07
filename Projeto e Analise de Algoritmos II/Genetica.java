import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genetica {
    
    Random geradorDeNumeros = new Random();

    public Genetica() {}

    public void gerarPopulacaoInicial(List<List<Integer>> populacao, Integer numeroDeItens, Integer numeroDeCromossomos) {

        for(int i = 0; i<numeroDeCromossomos; i++) {
            populacao.add(this.retornarIndividoAleatorio(numeroDeItens));
        }

    }

    private List<Integer> retornarIndividoAleatorio(Integer numeroDeItens) {
        List<Integer> individuo = new ArrayList<>();
        
        for(int i =0; i<numeroDeItens; i++) {
            individuo.add(geradorDeNumeros.nextInt(2));
        }

        return individuo;
    }

    public BigDecimal mediaDeFitness(List<Item> itens,List<List<Integer>> populacao, Integer pesoMaximo) {

        BigDecimal soma = BigDecimal.ZERO;
        for(int i =0; i<populacao.size(); i++) {
            soma = soma.add(this.fitness(populacao.get(i), pesoMaximo, itens));
        }

        return soma.divide(BigDecimal.valueOf(populacao.size()));
    }

    private BigDecimal fitness(List<Integer> individuo, Integer pesoMaximo, List<Item> itens) {
        Integer pesoTotal = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;

        for(int i = 0; i<individuo.size(); i++) {
            pesoTotal += individuo.get(i) * itens.get(i).getPeso();
            valorTotal = valorTotal.add(itens.get(i).getValor().multiply(BigDecimal.valueOf(individuo.get(i))));
        }

        if((pesoMaximo - pesoTotal) < 0) {
            return BigDecimal.ZERO;
        }
        return valorTotal;
    }

}
