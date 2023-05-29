package model;

public class Trabalho {
	
	public String codigo;
	public String tipo;
	public String tema;
	public String area;
	public String subarea;
	
	@Override
	public String toString() {
		return (codigo + ";" + tipo + ";" + tema + ";" + area + ";" + subarea );
	}

}
