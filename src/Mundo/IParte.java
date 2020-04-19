package Mundo;

public interface IParte {

	public void rotar();
	public void moverIzquiqerda();
	public void moverDerecha();
	public void bajar();
	public int darCentroX();
	public int darCentroY();
	public int[] darX();
	public int[] darY();
	public Pos[] darNuevosXY(int pX, int pY);
	public Pos[] tryRotate();
	public int darColor();
	
}
