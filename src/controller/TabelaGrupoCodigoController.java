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

	public Trabalho busca(Trabalho trabalho) throws Exception {
		int hash = trabalho.hashCodigo();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			Trabalho trbl = (Trabalho) l.get(i);
			if(trabalho.codigo.equals(trbl.codigo)) {
				return trbl;
			}
		}
		return null;
	}
	
	public String lista() throws Exception {
		StringBuffer trabalhos = new StringBuffer("");
		for (int i = 0; i < 10; i++) {
			ListaObject l = tabelaDeEspalhamento[i];
			int tamanho = l.size();
			
			for (int j = 0; j < tamanho; j++) {
				Trabalho trabalho = (Trabalho) l.get(j);
				trabalhos.append("Código: " + trabalho.codigo + ", Tipo: " + trabalho.tipo + ", Tema: " + trabalho.tema + ", Área:" + trabalho.area + ", Subárea: " + trabalho.subarea + ", Integrantes: " + trabalho.integrantes + System.getProperty("line.separator"));
			}
		}
		return trabalhos.toString();
		
	}
	

}
