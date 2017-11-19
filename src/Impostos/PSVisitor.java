package Impostos;

import BDProduto.Compravel;
import ItemVenda.ItemVenda;

public interface PSVisitor {
	public void visit(ItemVenda itemVenda);
	double getImposto();
}
