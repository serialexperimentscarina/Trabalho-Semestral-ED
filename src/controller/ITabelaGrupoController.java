package controller;

import model.Trabalho;

public interface ITabelaGrupoController {
	
	public void adiciona(Trabalho trabalho) throws Exception;
	public String busca(Trabalho trabalho) throws Exception;

}
