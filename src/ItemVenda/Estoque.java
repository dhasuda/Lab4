package ItemVenda;

public interface Estoque {
	
	public int disponibilidadeDeProduto(Compravel compravel);
	public void retirarProduto(Compravel compravel, int quantidade);
	public void devolverProduto(Compravel compravel, int quantidade);
	
}
