
public class Box {
	private int numero;
	private Herido[] plazas;
	public Box(int nBox, int nHeridos){
		numero = nBox;
		if (nHeridos < 0) plazas = null;
		else plazas = new Herido[nHeridos];
	}
	public int disponible() {
		if (plazas != null){
			int cont = 0;
			for (int i = 0; i<plazas.length; i++){
				if (plazas[i] == null) cont++;
			}
			return cont;
		}else return 0;
	}
	public int plazas(){
		if (plazas != null) return plazas.length;
		else return 0;
	}
	public int ingreso(Herido p) {
		if (plazas != null && p != null){
			for (int i = 0; i < plazas.length;i++){
				if (plazas[i] == null){
					plazas[i] = p;
					return i;
				}
			}
			return -1;
		} else return -1;
	}
	public boolean alta(Herido p) {
		if (plazas != null && p != null){
			for (int i=0; i < plazas.length;i++){
				if (plazas[i] != null && plazas[i].equals(p)){
					plazas[i] = null;
					return true;
				}
			}
			return false;
		} else return false;
	}
	public int estaIngresado(Herido p) {
		if (plazas != null && p != null){
			for (int i = 0; i < plazas.length;i++){
				if (plazas[i] != null && plazas[i].equals(p)){
					return i;
				}
			}
			return -1;
		} else return -1;
	}
	public Herido visita(int i){
		if (plazas != null && i<plazas.length && plazas[i] != null) return plazas[i];
		else return null;
	}
	public int getNumero(){
		return numero;
	}
}
