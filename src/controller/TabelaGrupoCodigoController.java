package controller;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Trabalho;

public class TabelaGrupoCodigoController implements ITabelaGrupoController{

	ListaObject[] tabelaDeEspalhamento;
	
	public TabelaGrupoCodigoController() {
		tabelaDeEspalhamento = new ListaObject[10];
		
		for (int i = 0; i < 10; i++) {
			tabelaDeEspalhamento[i] = new ListaObject();
		}
	}
	
	@Override
	public void adiciona(Trabalho trabalho) throws Exception {
		int hash = trabalho.hashCodigo();
		ListaObject l = tabelaDeEspalhamento[hash];
		
		l.addFirst(trabalho);
	}

	@Override
	public String busca(Trabalho trabalho) throws Exception {
		int hash = trabalho.hashCodigo();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			Trabalho trbl = (Trabalho) l.get(i);
			if(trabalho.codigo.equals(trbl.codigo)) {
				return ("Código: " + trbl.codigo + ", Tipo: " + trbl.tipo + ", Tema: " + trbl.tema + ", Área:" + trbl.area + ", Subárea: " + trbl.subarea);
			}
		}
		return null;
	}
	

}
