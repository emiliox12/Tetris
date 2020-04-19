package Mundo;

public interface IParte {

	public void rotar();
	public int[][] moverIzquiqerda();
	public int[][] moverDerecha();
	public void bajar();
	public int darCentroX();
	public int darCentroY();
	public int[] darX();
	public int[] darY();
	public int[] darNuevosX(int pX, int pY);
	public int[] darVuevosY(int pX, int pY);
	public int darColor();
	
}
