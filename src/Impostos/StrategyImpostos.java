package Impostos;

import java.util.List;
import java.util.Set;

import ItemVenda.ItemVenda;
import NotaFiscal.NotaFiscal;

public class StrategyImpostos {
	private BDTax bdTax = BDTax.getInstance();
	private List<ItemVenda> itemList;
	
	public StrategyImpostos(List<ItemVenda> itensVenda) {
		this.itemList = itensVenda;
		this.executarTaxacoes();
	}
	
	private void executarTaxacoes() {
		Context taxado;
		Set<PSVisitor> impostos = bdTax.getImpostos();
		for (ItemVenda item: itemList) {
			for(PSVisitor imposto : impostos) {
				taxado = new Context(imposto);
				taxado.visit(item);
				item.setImposto(taxado.getTaxacao());
			}
			
		}
	}
}
