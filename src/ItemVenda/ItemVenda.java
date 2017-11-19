package ItemVenda;

import java.util.Collections;
import java.util.List;

import BDProduto.Compravel;

public class ItemVenda {
	private Compravel venda;
	private int quantidade;
	private double desconto;
	private String categoriaTributaria;
	private List<Compravel> subCompraveis;
	private double imposto;
	
	public ItemVenda(Compravel venda, int quantidade) {
		this.venda = venda;
		this.quantidade = quantidade;
		this.desconto = 0.0;
		this.subCompraveis = venda.getListaSubCompraveis();
	}
	public ItemVenda(Compravel venda, int quantidade, double desconto) {
		this.venda = venda;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.subCompraveis = venda.getListaSubCompraveis();
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
	
	public double getPrecoTaxado() {
		return this.venda.getPreco() * (double)this.quantidade * (1-this.desconto) + this.imposto;
	}
	
	public void setTaxacao(double imposto) {
		this.imposto = imposto;
	}
	
	public String getCategoriaTributaria() {
		return venda.getCategoriaTributaria();
	}
	
	public List<Compravel> getListaSubCompraveis() {
		return Collections.unmodifiableList(subCompraveis);
	}
}
