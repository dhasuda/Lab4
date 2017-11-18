package NotaFiscal;
import java.util.List;

import BDProduto.BDProduto;
import BDProduto.Compravel;
import ItemVenda.Estoque;
import ItemVenda.ItemVenda;

import java.util.ArrayList;
import java.util.Collections;

public class NotaFiscal {
	private int id;
	private Estoque estoque;
	private List<ItemVenda> itemList;
	private BDProduto bdProdutos;
	private String status;
	private Validador validador;
	
	// Constructor
	public NotaFiscal(int quantidadeProduto, String nomeProduto, BDProduto vendas, Validador validador, Estoque estoque) throws NullPointerException {
		this.estoque = estoque;
		this.status = "em elaboracao";
		this.id = -1;
		this.bdProdutos = vendas;
		itemList = new ArrayList<ItemVenda>();
		this.validador = validador;
		
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
	
	public boolean addItem(String itemName, int quantidade) {
		if (this.podeMudar()) {
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
	
	public boolean removeItem(String toRemove) {
		if (this.podeMudar()) {
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
	
	boolean setId(int id) {
		if (this.id == -1 && this.podeMudar()) {
			this.id = id;
			return true;
		}
		return false;
	}
	
	private boolean podeMudar() {
		if (this.status == "em elaboracao")
			return true;
		return false;
	}
	
	void validar() {
		if (this.podeMudar()) {
			try {
				if (validador.isValid(this)) {
					this.status = "validada";
					// TODO: Enviar para a prefeitura; get new ID etc
				}
			} catch(Exception e) {
				
			}
		}
		System.out.println("A nota fiscal ja foi validada");
	}
	
	public void printNF() {
		if (this.podeMudar()) {
			System.out.println("ID ainda não definido");
		}
		for (ItemVenda item : itemList) {
			System.out.println(item.toString());
		}
		System.out.println(status);
	}
	
}