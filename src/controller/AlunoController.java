package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Aluno;

public class AlunoController implements ActionListener{

	private JTextField tfAlunoNome;
	private JTextField tfAlunoRa;
	private JTextArea taAlunoLista;
	private JTextField tfAlunoBusca;
	
	public AlunoController(JTextField tfAlunoNome, JTextField tfAlunoRa, JTextArea taAlunoLista,
			JTextField tfAlunoBusca) {
		this.tfAlunoNome = tfAlunoNome;
		this.tfAlunoRa = tfAlunoRa;
		this.taAlunoLista = taAlunoLista;
		this.tfAlunoBusca = tfAlunoBusca;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Gravar")) {
			try {
				gravar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Buscar")) {
			try {
				buscar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}


	private void gravar() throws Exception {
		Aluno aluno = new Aluno();
		aluno.nome = tfAlunoNome.getText();
		aluno.ra = tfAlunoRa.getText();
		
		System.out.println("Aluno [" + aluno + "]");
		
		gravaAluno(aluno.toString());
	}
	

	private void gravaAluno(String csvAluno) throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File dir = new File(path);
		
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		File arq = new File(path, "aluno.csv");
		boolean arqExiste = arq.exists();
		
		FileWriter fw = new FileWriter(arq, arqExiste);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvAluno + System.getProperty("line.separator"));
		pw.flush();
		pw.close();
		fw.close();
		
	}



	private void buscar() throws Exception {
		Aluno aluno = new Aluno();
		aluno.ra = tfAlunoBusca.getText();
		
		aluno = buscaAluno(aluno);
		if (aluno.nome != null) {
			taAlunoLista.setText("Nome: " + aluno.nome + "; RA: " + aluno.ra);
		} else {
			JOptionPane.showMessageDialog(null, "Aluno não encontrado");
		}
	}



	private Aluno buscaAluno(Aluno aluno) throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "aluno.csv");
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[1].equals(aluno.ra)) {
					aluno.nome = vetLinha[0];
					break;
				}
				
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return aluno;
	}	
	
}