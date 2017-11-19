package Impostos;

import ItemVenda.ItemVenda;

public class Imposto1 implements PSVisitor {
	double aliquota;
	double preco;
	int quantidade;
	String categoriaTributaria;
	
	public void visit(ItemVenda itemVenda) {
		preco = itemVenda.getPreco();
		quantidade = itemVenda.getQuantidade();
		categoriaTributaria = itemVenda.getCategoriaTributaria();
	}
	
	private void definicaoAliquota() {
		aliquota = 0.01;
		
		switch(categoriaTributaria){
		case "Automovel":
			aliquota = 0.1;
			break;
			
		case "Eletrodomestico":
			aliquota = 0.05;
			break;
		
		case "Alimentacao":
			aliquota = 0.02;
			break;
		}
	}
	
	private double calculoImposto() {
		definicaoAliquota();
		return preco * aliquota;
		
	}
	
	public double getImposto() {
		return calculoImposto();
	}
}
