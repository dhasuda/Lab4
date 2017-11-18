package BDProduto;

public class Servico implements Compravel {
	private String nome;
	private double preco;
	private String setor;
	private String categoriaTributaria;
	
	Servico(String nome, double preco, String setor, String categoriaTributaria){
		this.nome = nome;
		this.preco = preco;
		this.setor = setor;
		this.categoriaTributaria = categoriaTributaria;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public double getPreco() {
		return this.preco;
	}
	
	@Override
	public String getSetorResponsavel() {
		return this.setor;
	}
	
	@Override
	public String getCategoriaTributaria() {
		return this.categoriaTributaria;
	}

}
