package model;

import br.com.serialexperimentscarina.listastrings.ListaStrings;

public class Area {
	
	public int codigo;
	public String nome;
	public String descricao;
	public ListaStrings subareas = new ListaStrings();
	
	@Override
	public String toString() {
		String stringSubareas = "\"";
		int numSubareas = subareas.size();
		
		for (int i = 0; i < numSubareas; i++) {
			try {
				stringSubareas += (subareas.get(i) + ";");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stringSubareas = (stringSubareas.substring(0, stringSubareas.length() - 1) + "\"");
		
		return (codigo + ";" + nome + ";" + descricao + ";" + stringSubareas);
	}

}
