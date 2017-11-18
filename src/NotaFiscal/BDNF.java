package NotaFiscal;

import java.util.HashSet;
import java.util.Set;

import NotaFiscal.NotaFiscal;

public class BDNF {
	private static BDNF _bdnf = new BDNF();
	private static int _id;
	private Set<NotaFiscal> notasFiscais = new HashSet<NotaFiscal>();
	private Prefeitura _prefeitura;
	
	public void recebePrefeitura(Prefeitura prefeitura){
		_prefeitura = prefeitura;
	}
	
	static BDNF getInstance() {
		return _bdnf;
	}
	
	NotaFiscal validarNF(NotaFiscal notaFiscal) throws Exception {
		if(notaFiscal.getStatus() == "em elaboracao") {
			if (isConsistente(notaFiscal)) {
				notaFiscal.setStatus("validada");
				notaFiscal.setId(++_id);
				notasFiscais.add(notaFiscal);
				final NotaFiscal notaFiscalImutavel = notaFiscal;
				_prefeitura.recebeNovaNF(notaFiscalImutavel);
				return notaFiscalImutavel;
			}
			notaFiscal.setStatus("invalida");
			throw new Exception("A nota fiscal nao foi aceita pelo Banco de Dados.\n");
		}
		throw new Exception("A nota fiscal nao pode ser validada novamente pelo Banco de Dados.\n");
	}
	
	boolean isConsistente(NotaFiscal notaFiscal) {
		/* Implementar */
		return true;
	}
}
