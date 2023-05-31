package model;

public class Aluno {
	
	public String nome;
	public String ra;
	
	@Override
	public String toString() {
		return (nome + ";" + ra);
	}
	
	@Override
	public int hashCode() {
		return Integer.parseInt(ra.substring(ra.length() - 1));
	}

}
