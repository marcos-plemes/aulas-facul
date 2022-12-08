import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Item> itens = Arrays.asList(
            new Item("Balcão da Cozinha", 41, BigDecimal.valueOf(439.00)),
            new Item("Armario da Cozinha", 54, BigDecimal.valueOf(517.00)),
            new Item("Guarda Roupa", 150, BigDecimal.valueOf(1241.00)),
            new Item("Mesa", 46, BigDecimal.valueOf(397.00)),
            new Item("Mesa para Escritório", 122, BigDecimal.valueOf(934.00)),
            new Item("Painel da TV", 61, BigDecimal.valueOf(494.00)),
            new Item("Estante de Livros", 48, BigDecimal.valueOf(394.00)),
            new Item("Estante", 33, BigDecimal.valueOf(297.00)),
            new Item("Rack", 51, BigDecimal.valueOf(501.00)),
            new Item("Cômoda", 43, BigDecimal.valueOf(447.00))
        );

        Genetica genetica = new Genetica(450,70,115, itens);

        genetica.build();

        
    }
}