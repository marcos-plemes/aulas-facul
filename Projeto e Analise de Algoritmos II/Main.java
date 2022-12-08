import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Item> itens = Arrays.asList(
            new Item("Balcão da Cozinha", 41, BigDecimal.valueOf(439.89)),
            new Item("Armario da Cozinha", 54, BigDecimal.valueOf(517.89)),
            new Item("Guarda Roupa", 150, BigDecimal.valueOf(1241.79)),
            new Item("Mesa", 46, BigDecimal.valueOf(397.97)),
            new Item("Mesa para Escritório", 122, BigDecimal.valueOf(934.89)),
            new Item("Painel da TV", 61, BigDecimal.valueOf(494.10)),
            new Item("Estante de Livros", 48, BigDecimal.valueOf(394.95)),
            new Item("Estante", 33, BigDecimal.valueOf(297.95)),
            new Item("Rack", 51, BigDecimal.valueOf(501.97)),
            new Item("Cômoda", 43, BigDecimal.valueOf(447.97))
        );

        Genetica genetica = new Genetica(500,70,115, itens);

        genetica.build();

        
    }
}