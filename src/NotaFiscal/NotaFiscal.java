package NotaFiscal;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import BDProduto.BDProduto;
import BDProduto.ItemVenda;
import BDProduto.Compravel;

public class NotaFiscal {
	private int id;
	private List<ItemVenda> itemList;
	private BDProduto bdProdutos;
	private String status;
	private boolean podeMudar = true;
	
	// Constructor
	public NotaFiscal(int quantidadeProduto, String nomeProduto, BDProduto vendas) throws NullPointerException {
		this.status = "em elaboracao";
		this.id = -1;
		this.bdProdutos = vendas;
		itemList = new ArrayList<ItemVenda>();

		
		try {
			Compravel venda = vendas.getCompravel(nomeProduto);
			ItemVenda newItem = new ItemVenda(venda, quantidadeProduto);
			itemList.add(newItem);
		}
		catch (NullPointerException e){
			throw e;
		}

	} // End constructor
	
	
	public List<ItemVenda> getItemList() {
		return Collections.unmodifiableList(this.itemList);
	}
	
	public void addItem(String itemName, int quantidade) {
		if (this.podeMudar) {
			Compravel compra = bdProdutos.getCompravel(itemName);
			ItemVenda newItem = new ItemVenda(compra, quantidade);
			itemList.add(newItem);
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean removeItem(String toRemove) {
		if (this.podeMudar) {
			if (itemList.size() > 1) {
				int count = 0;
				for (ItemVenda item: itemList) {
					if (item.getVenda().getNome().equals(toRemove)) {
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
	
	boolean setId(int id) {
		if (this.id == -1) {
			this.id = id;
			return true;
		}
		return false;
	}
	
	boolean validar() {
		if (this.status.equals("em elaboracao") && id!=-1) {
			this.status = "validada";
			this.podeMudar = false;
			return true;
		}
		return false;
	}
	
	public void printNF() {
		if (this.status.equals("em elaboracao")) {
			System.out.println("ID ainda não definido");
		}
		for (ItemVenda item : itemList) {
			System.out.println(item.toString());
		}
		System.out.println(status);
	}
	
}