package model;

public class Orientacao {
	
	public int dia;
	public int mes;
	public int ano;
	public String pontos;
	
	@Override
	public String toString() {
		return (dia + ";" + mes + ";" + ano + ";" + pontos);
	}

}
