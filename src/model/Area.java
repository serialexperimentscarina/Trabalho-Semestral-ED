package model;

import br.com.serialexperimentscarina.listastrings.ListaStrings;

public class Area {
	
	public int codigo;
	public String nome;
	public String descricao;
	public ListaStrings subareas = new ListaStrings();
	
	@Override
	public String toString() {
		StringBuffer stringSubareas = new StringBuffer("\"");
		int numSubareas = subareas.size();
		
		for (int i = 0; i < numSubareas; i++) {
			try {
				stringSubareas.append(subareas.get(i) + ";");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (codigo + ";" + nome + ";" + descricao + ";" + (stringSubareas.toString().substring(0, stringSubareas.length() - 1)) + "\"");
	}

	
	@Override
	public int hashCode() {
		return (nome.toLowerCase().charAt(0)) % 10;
	}
	
}
