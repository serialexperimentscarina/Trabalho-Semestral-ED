package controller;

import javax.swing.JOptionPane;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Aluno;

public class TabelaAlunoController {
	
ListaObject[] tabelaDeEspalhamento;
	
	public TabelaAlunoController() {
		tabelaDeEspalhamento = new ListaObject[10];
		
		for (int i = 0; i < 10; i++) {
			tabelaDeEspalhamento[i] = new ListaObject();
		}
	}
	
	public void adiciona(Aluno aluno) throws Exception {
		int hash = aluno.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		
		l.addFirst(aluno);
	}

	public Aluno busca(Aluno aluno) throws Exception {
		int hash = aluno.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			Aluno al = (Aluno) l.get(i);
			if(al.ra.equals(aluno.ra)) {
				return al;
			}
		}
		return null;
	}
	
	public boolean remove(Aluno aluno) throws Exception {
		int hash = aluno.hashCode();
		ListaObject l = tabelaDeEspalhamento[hash];
		int tamanho = l.size();
		
		for (int i = 0; i < tamanho; i++) {
			Aluno al = (Aluno) l.get(i);
			if(al.ra.equals(aluno.ra)) {
				l.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public String lista() throws Exception {
		StringBuffer alunos = new StringBuffer("");
		for (int i = 0; i < 10; i++) {
			ListaObject l = tabelaDeEspalhamento[i];
			int tamanho = l.size();
			
			for (int j = 0; j < tamanho; j++) {
				Aluno aluno = (Aluno) l.get(j);
				alunos.append("Nome: " + aluno.nome + "; RA: " + aluno.ra + System.getProperty("line.separator"));
			}
		}
		
		return alunos.toString();
		
	}

}
