import java.math.BigDecimal;

public class Item {

    private String nome;

    private Integer peso;

    private BigDecimal valor;

    public Item() {}

	public Item(
			String nome,
			Integer peso, 
			BigDecimal valor) {
		this.nome = nome;
		this.peso = peso;
		this.valor = valor;
	}

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

    
}
