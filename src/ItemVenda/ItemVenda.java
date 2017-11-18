package ItemVenda;

import BDProduto.Compravel;

public class ItemVenda {
	private Compravel venda;
	private int quantidade;
	private double desconto;
	
	public ItemVenda(Compravel venda, int quantidade) {
		this.venda = venda;
		this.quantidade = quantidade;
		this.desconto = 0.0;
	}
	public ItemVenda(Compravel venda, int quantidade, double desconto) {
		this.venda = venda;
		this.quantidade = quantidade;
		this.desconto = desconto;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public Compravel getVenda() {
		return this.venda;
	}
	
	public double getPreco() {
		return this.venda.getPreco() * (double)this.quantidade * (1-this.desconto);
	}
}
