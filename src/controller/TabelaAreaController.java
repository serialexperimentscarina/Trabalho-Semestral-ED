package controller;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Area;

public class TabelaAreaController {
	
ListaObject[] tabelaDeEspalhamento;
	
	public TabelaAreaController() {
		tabelaDeEspalhamento = new ListaObject[10];
		
		for (int i = 0; i < 10; i++) {
			tabelaDeEspalhamento[i] = new ListaObject();
		}
	}
	
	public void adiciona(Area area) throws Exception {
		int hash = area.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		
		l.addFirst(area);
	}

	public Area busca(Area area) throws Exception {
		int hash = area.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			Area ar = (Area) l.get(i);
			if(ar.nome.equals(area.nome)) {
				return ar;
			}
		}
		return null;
	}
	
	public boolean remove(Area area) throws Exception {
		int hash = area.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			Area ar = (Area) l.get(i);
			if(ar.nome.equals(area.nome)) {
				l.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public String lista() throws Exception {
		StringBuffer areas = new StringBuffer("");
		for (int i = 0; i < 10; i++) {
			ListaObject l = tabelaDeEspalhamento[i];
			int tamanho = l.size();
			
			for (int j = 0; j < tamanho; j++) {
				Area area = (Area) l.get(j);
				String stringSubareas = "; Subáreas: ";
				int numSubareas = area.subareas.size();
				for (int k = 0; k < numSubareas; k++) {
					stringSubareas += (area.subareas.get(k) + ", ");
				}
				areas.append("Código: " + area.codigo + "; Área: " + area.nome + "; Descrição: " + area.descricao + stringSubareas.substring(0, stringSubareas.length() - 2) + System.getProperty("line.separator"));
			}
		}
		return areas.toString();
		
	}

}