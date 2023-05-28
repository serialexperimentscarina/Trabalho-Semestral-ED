package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.serialexperimentscarina.listaobject.ListaObject;
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
		try {
			switch (cmd) {
				case "Gravar":
					gravar();	
					break;
				case "Buscar":
					buscar();	
					break;
				case "Upload por CSV":
					upload();	
					break;
				default:
					break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	private void gravar() throws Exception {
		Aluno aluno = new Aluno();
		aluno.nome = tfAlunoNome.getText();
		aluno.ra = tfAlunoRa.getText();
		
		if (!aluno.nome.equals("") && (!aluno.ra.equals("") && aluno.ra.matches("[0-9]+"))) {
			gravaAluno(aluno.toString());
			
			tfAlunoNome.setText("");
			tfAlunoRa.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Um ou mais campos vazios ou possuem caracteres inválidos", "ERRO!", JOptionPane.ERROR_MESSAGE);
		}

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
			JOptionPane.showMessageDialog(null, "Aluno não encontrado!", "ERRO!", JOptionPane.ERROR_MESSAGE);
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
	
	private void upload() throws Exception {
		UploadController uploadCrtl = new UploadController();
		File arquivo = uploadCrtl.uploadArquivo();
		ListaObject listaAluno = new ListaObject();
		
		if (arquivo != null) {
			FileInputStream fInStr = new FileInputStream(arquivo);
			InputStreamReader InStrReader = new InputStreamReader(fInStr);
			BufferedReader bufferReader = new BufferedReader(InStrReader);
			String linha = bufferReader.readLine();
			
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				
				// Checa se todos os campos do CSV estão corretos
				if (vetLinha.length == 2 && !vetLinha[0].equals("") && (!vetLinha[1].equals("") && vetLinha[1].matches("[0-9]+"))) {
					Aluno aluno = new Aluno();
					aluno.nome = vetLinha[0];
					aluno.ra = vetLinha[1];
					listaAluno.addFirst(aluno);
				} else {
					JOptionPane.showMessageDialog(null, "Um ou mais campos inválidos passados por CSV, verifique seu arquivo e tente novamente", "ERRO!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();
			
			//Se todos os alunos passados forem válidos, adiciona eles ao sistema
			while (!listaAluno.isEmpty()) {
				gravaAluno(listaAluno.get(0).toString());
				listaAluno.removeFirst();
			}
			
			JOptionPane.showMessageDialog(null, "Upload feito com sucesso", "Upload concluído", JOptionPane.PLAIN_MESSAGE);
			
		}
	}
	
}