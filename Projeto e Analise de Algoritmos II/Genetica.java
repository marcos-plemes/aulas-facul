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

}
