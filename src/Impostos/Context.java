package Impostos;

import ItemVenda.ItemVenda;

public class Context {
	private PSVisitor taxado;
	
	public Context(PSVisitor imposto) {
		this.taxado = imposto;
	}
	
	void visit(ItemVenda itemVenda) {
		taxado.visit(itemVenda);
	}
	
	public double getTaxacao(){
		return taxado.getImposto();
	}
}
