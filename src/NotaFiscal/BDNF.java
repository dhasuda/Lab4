package NotaFiscal;

import java.util.HashSet;
import java.util.Set;

import NotaFiscal.NotaFiscal;

public class BDNF {
	private static BDNF _bdnf = new BDNF();
	private static int _id;
	private String _state = "invalida";
	private Set<NotaFiscal> notasFiscais = new HashSet<NotaFiscal>();
	
	static BDNF getInstance() {
		return _bdnf;
	}
	
	boolean isValid(NotaFiscal notaFiscal) throws Exception {
		if (!notaFiscal.getItemList().isEmpty()) {
			_state = "valida";
			_id++;
			notasFiscais.add(notaFiscal);
			return true;
		}
		_state = "invalida";
		throw new Exception("A nota fiscal nao foi aceita pelo Banco de Dados");
	}
	
	int getId() {
		if (_state == "valida")
			return _id;
		System.out.println("A nota fiscal nao foi validada pelo Banco de Dados");
		return -1;
	}
}
