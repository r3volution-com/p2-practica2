
public class Box {
	private int numero;
	private Herido[] plazas;
	public Box(int nBox, int nHeridos){
		numero = nBox;
		if (nHeridos < 0) plazas = null;
		else plazas = new Herido[nHeridos];
	}
}
