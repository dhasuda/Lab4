package NotaFiscal;

import NotaFiscal.NotaFiscal;

public class Validador {
	private NotaFiscal _notaFiscal;
	private int _id;
	private BDNF _bdnf;
	
	private void getBDNF() {
		_bdnf = BDNF.getInstance();
	}
	
	boolean isValid(NotaFiscal notaFiscal) throws Exception {
		try {
			if (_bdnf.isValid(notaFiscal)) {
				_id = _bdnf.getId();
				return true;
			}
		} catch (Exception e){
			
		}
		return false;
	}
	
	int getId () {
		return _id;
	}
	
	
	
}
