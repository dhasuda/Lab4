package Impostos;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BDTax {
	private static BDTax bdTax = new BDTax();
	Set<PSVisitor> impostos = new HashSet<PSVisitor>();
	
	public static BDTax getInstance() {
		return bdTax;
	}
	
	public Set<PSVisitor> getImpostos(){
		return Collections.unmodifiableSet(impostos);
	}
}
