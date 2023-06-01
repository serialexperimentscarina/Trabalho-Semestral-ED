package model;

import br.com.serialexperimentscarina.pilhaobject.PilhaObject;

public class Trabalho {
	
	public String codigo;
	public String tipo;
	public String tema;
	public String area;
	public String subarea;
	public String integrantes;
	
	public PilhaObject orientacoes = new PilhaObject();
	
	@Override
	public String toString() {
		return (codigo + ";" + tipo + ";" + tema + ";" + area + ";" + subarea + ";" + integrantes);
	}

	
	public int hashCodigo() {
		return (Integer.parseInt(codigo) % 10);
	}
	
	public int hashSubarea() {
		return (subarea.toLowerCase().charAt(0) - 97);
	}

}
