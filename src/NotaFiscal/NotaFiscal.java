package NotaFiscal;
import java.util.List;

import BDProduto.BDProduto;
import BDProduto.Compravel;
import Impostos.BDTax;
import Impostos.StrategyImpostos;
import ItemVenda.Estoque;
import ItemVenda.ItemVenda;

import java.util.ArrayList;
import java.util.Collections;

public class NotaFiscal {
	private int id;
	private Estoque estoque;
	private List<ItemVenda> itemList;
	private BDNF bdNF;
	private BDProduto bdProdutos;
	private String status;
	private BDTax bdTax;
	
	// Constructor
	public NotaFiscal(int quantidadeProduto, String nomeProduto, BDProduto vendas, Estoque estoque) throws NullPointerException {
		this.estoque = estoque;
		this.status = "em elaboracao";
		this.bdNF = BDNF.getInstance();
		this.bdProdutos = vendas;
		this.bdTax = BDTax.getInstance();
		itemList = new ArrayList<ItemVenda>();
		
		try {
			Compravel venda = vendas.getCompravel(nomeProduto);
			if (estoque.disponibilidadeDeProduto(venda) >= quantidadeProduto) {
				ItemVenda newItem = new ItemVenda(venda, quantidadeProduto);
				estoque.retirarProduto(venda, quantidadeProduto);
				itemList.add(newItem);
			} else {
				/*
				 * TODO: Refactor this part!
				 */
				throw new NullPointerException();
			}
		}
		catch (NullPointerException e){
			throw e;
		}

	} // End constructor
	
	
	public List<ItemVenda> getItemList() {
		return Collections.unmodifiableList(this.itemList);
	}
	
	private void taxar() {
		new StrategyImpostos(this.itemList);
	}
	
	public boolean addItem(String itemName, int quantidade) {
		if (this.status == "em elaboracao") {
			Compravel compra = bdProdutos.getCompravel(itemName);
			if (estoque.disponibilidadeDeProduto(compra) >= quantidade) {
				estoque.retirarProduto(compra, quantidade);
				ItemVenda newItem = new ItemVenda(compra, quantidade);
				itemList.add(newItem);
				return true;
			}
		}
		return false;
	}
	
	public int getId() {
		return this.id;
	}
	
	void setId(int id) {
		this.id = id;
	}
	
	public boolean removeItem(String toRemove) {
		if (this.status == "em elaboracao") {
			if (itemList.size() > 1) {
				int count = 0;
				for (ItemVenda item: itemList) {
					if (item.getVenda().getNome().equals(toRemove)) {
						estoque.devolverProduto(item.getVenda(), item.getQuantidade());
						itemList.remove(count);
						return true;
					}
					count ++;
				}
			}
		}
		return false;
	}
	
	public double getPreco() {
		double total = 0;
		for (ItemVenda item : itemList) {
			total += item.getPreco();
		}
		return total;
	}

	void validar() {
		try {
			if (this.status == "em elaboracao") {
				this.taxar();
				bdNF.validarNF(this);
			}
			throw new Exception("A nota fiscal nao pode ser validada novamente pelo Banco de Dados.\n");
		} catch (Exception e) {
		
		}
	}
	
	void setStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return this.status;
	}
	
	public void printNF() {
		if (this.status == "em elaboracao") {
			System.out.println("ID ainda não definido");
		}
		for (ItemVenda item : itemList) {
			System.out.println(item.toString());
		}
		System.out.println(status);
	}
	
}