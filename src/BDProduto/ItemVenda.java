package BDProduto;

public class ItemVenda {
	private Compravel venda;
	private int quantidade;
	
	public ItemVenda(Compravel venda, int quantidade) {
		this.venda = venda;
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public Compravel getVenda() {
		return this.venda;
	}
	
	public double getPreco() {
		return this.venda.getPreco() * (double)this.quantidade;
	}
}
