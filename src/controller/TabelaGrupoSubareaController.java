package controller;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Trabalho;

public class TabelaGrupoSubareaController implements ITabelaGrupoController{

	ListaObject[] tabelaDeEspalhamento;
	
	public TabelaGrupoSubareaController() {
		tabelaDeEspalhamento = new ListaObject[26];
		
		for (int i = 0; i < 26; i++) {
			tabelaDeEspalhamento[i] = new ListaObject();
		}
	}
	
	@Override
	public void adiciona(Trabalho trabalho) throws Exception {
		int hash = trabalho.hashSubarea();
		ListaObject l = tabelaDeEspalhamento[hash];
		
		l.addFirst(trabalho);
	}

	@Override
	public String busca(Trabalho trabalho) throws Exception {
		int hash = trabalho.hashSubarea();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		String trabalhos = "";
		for (int i = 0; i < tamanho; i++) {
			Trabalho trbl = (Trabalho) l.get(i);
			if(trabalho.subarea.equals(trbl.subarea)) {
				trabalhos += ("Código: " + trbl.codigo + ", Tipo: " + trbl.tipo + ", Tema: " + trbl.tema + ", Área:" + trbl.area + ", Subárea: " + trbl.subarea + System.getProperty("line.separator"));
			}
		}
		
		return !trabalho.equals("") ? trabalhos : null;
	}
	

}
