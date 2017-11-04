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
	
	// Constructor
	public NotaFiscal(int quantidadeProduto, String nomeProduto, BDProduto vendas) throws NullPointerException {
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
		Compravel compra = bdProdutos.getCompravel(itemName);
		ItemVenda newItem = new ItemVenda(compra, quantidade);
		itemList.add(newItem);
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean removeItem(String toRemove) {
		if (itemList.size() > 1) {
			int count = 0;
			for (ItemVenda item: itemList) {
				if (item.getVenda().getNome().equals(toRemove)) {
					//Item removedItem = new Item(item.getVenda(), item.getQuantidade());
					itemList.remove(count);
					return true;
				}
				count ++;
			}
		}
		return false;
		
		//		if (itemList.size() > 1) {
//			return itemList.remove(toRemove);
//		}
//		return false;
	}
	
	public double getPreco() {
		double total = 0;
		for (ItemVenda item : itemList) {
			total += item.getPreco();
		}
		return total;
	}
	
}