package model;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Classe responsável por representar um produto.
 * 
 * A classe Produto contém informações sobre um produto, como seu ID, nome, valor, quantidade e imagem.
 * 
 * Atributos:
 * - id_produto: ID do produto.
 * - nome_produto: nome do produto.
 * - valor_produto: valor do produto.
 * - qtd_produto: quantidade disponível do produto.
 * - imagem_produto: URL da imagem do produto.
 * 
 * Métodos:
 * - getters e setters: permitem acessar e modificar os atributos da classe.
 * - construtores: permitem criar instâncias da classe com diferentes conjuntos de atributos.
 * - hashCode e equals: são implementados para permitir a comparação de objetos Produto.
 * - toString: retorna uma representação em formato de string do objeto Produto.
 * 
 * Exemplo de uso:
 * 
 * Produto produto = new Produto();
 * produto.setNome_produto("Produto 1");
 * produto.setValor_produto(10.99);
 * produto.setQtd_produto(20);
 * produto.setImagem_produto("https://example.com/produto1.jpg");
 * 
 * System.out.println(produto.getNome_produto()); // Imprime o nome do produto
 * System.out.println(produto.getValor_produto()); // Imprime o valor do produto
 * System.out.println(produto.getQtd_produto()); // Imprime a quantidade do produto
 * System.out.println(produto.getImagem_produto()); // Imprime a URL da imagem do produto
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @see services.ProdutoService
 * @see dao.ProdutoDao
 * @see controller.ProdutoResource
 * @see model.Produto
 * 
 * @author Stockwave
 * 
 */
public class Produto {
	
	/**
	 * ID do Produto
	 */
	private int id_produto;
	
	/**
	 * Nome do Produto
	 */
	@NotNull(message = "O nome do produto não pode ser nulo.")
	private String nome_produto;
	
	/**
	 * Valor do Produto
	 */
	@NotNull(message = "O valor do produto não pode ser nulo.")
	@Positive(message = "O valor do produto deve ser maior que zero.")
	private double valor_produto;
	
	/**
	 * Quantidade do Produto
	 */
	private int qtd_produto;
	
	/**
	 * URL da imagem do Produto
	 */
	@NotNull(message = "A imagem do produto não pode ser nula.")
	private String imagem_produto;
	
	/**
	 * Obtém o ID do Produto.
	 * 
	 * @return o ID do Produto.
	 */
	public int getId_produto() {
		return id_produto;
	}

	/**
	 * Define o ID do Produto.
	 * 
	 * @param id_produto o ID do Produto.
	 */
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	/**
	 * Obtém o nome do Produto.
	 * 
	 * @return o nome do Produto.
	 */
	public String getNome_produto() {
		return nome_produto;
	}

	/**
	 * Define o nome do Produto.
	 * 
	 * @param nome_produto o nome do Produto.
	 */
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	/**
	 * Obtém o valor do Produto.
	 * 
	 * @return o valor do Produto.
	 */
	public double getValor_produto() {
		return valor_produto;
	}

	/**
	 * Define o valor do Produto.
	 * 
	 * @param valor_produto o valor do Produto.
	 */
	public void setValor_produto(double valor_produto) {
		this.valor_produto = valor_produto;
	}

	/**
	 * Obtém a quantidade do Produto.
	 * 
	 * @return a quantidade do Produto.
	 */
	public int getQtd_produto() {
		return qtd_produto;
	}

	/**
	 * Define a quantidade do Produto.
	 * 
	 * @param qtd_produto a quantidade do Produto.
	 */
	public void setQtd_produto(int qtd_produto) {
		this.qtd_produto = qtd_produto;
	}

	/**
	 * Obtém a URL da imagem do Produto.
	 * 
	 * @return a URL da imagem do Produto.
	 */
	public String getImagem_produto() {
		return imagem_produto;
	}

	/**
	 * Define a URL da imagem do Produto.
	 * 
	 * @param imagem_produto a URL da imagem do Produto.
	 */
	public void setImagem_produto(String imagem_produto) {
		this.imagem_produto = imagem_produto;
	}
	
	/**
	 * Construtor padrão da classe Produto.
	 */
	public Produto() {
		super();
	}
	
	/**
	 * Construtor não padrão da classe Produto.
	 * 
	 * @param id_produto o ID do Produto.
	 * @param nome_produto o nome do Produto.
	 * @param valor_produto o valor do Produto.
	 * @param qtd_produto a quantidade do Produto.
	 * @param imagem_produto a URL da imagem do Produto.
	 */
	public Produto(int id_produto, @NotNull(message = "O nome do produto não pode ser nulo.") String nome_produto,
			@NotNull(message = "O valor do produto não pode ser nulo.") @Positive(message = "O valor do produto deve ser maior que zero.") double valor_produto,
			int qtd_produto, @NotNull(message = "A imagem do produto não pode ser nula.") String imagem_produto) {
		super();
		this.id_produto = id_produto;
		this.nome_produto = nome_produto;
		this.valor_produto = valor_produto;
		this.qtd_produto = qtd_produto;
		this.imagem_produto = imagem_produto;
	}
	
	/**
	 * Retorna o hash code do objeto Produto.
	 * 
	 * @return o hash code do objeto Produto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id_produto, imagem_produto, nome_produto, qtd_produto, valor_produto);
	}
	
	/**
	 * Verifica se o objeto Produto é igual a outro objeto.
	 * 
	 * @param obj o objeto a ser comparado.
	 * @return true se os objetos são iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return id_produto == other.id_produto && Objects.equals(imagem_produto, other.imagem_produto)
				&& Objects.equals(nome_produto, other.nome_produto) && qtd_produto == other.qtd_produto
				&& Double.compare(valor_produto, other.valor_produto) == 0;
	}
	
	/**
	 * Retorna uma representação em formato de string do objeto Produto.
	 * 
	 * @return uma representação em formato de string do objeto Produto.
	 */
	@Override
	public String toString() {
		return "Produto [id_produto=" + id_produto + ", nome_produto=" + nome_produto + ", valor_produto="
				+ valor_produto + ", qtd_produto=" + qtd_produto + ", imagem_produto=" + imagem_produto + "]";
	}
}
