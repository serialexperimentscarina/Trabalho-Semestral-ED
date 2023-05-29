package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Aluno;
import model.Trabalho;

public class TrabalhoController implements ActionListener {

	private JTextField tfTrabalhoCodigo;
	private JTextField tfTrabalhoTipo;
	private JTextField tfTrabalhoTema;
	private JTextField tfTrabalhoArea;
	private JTextField tfTrabalhoSubarea;

	public TrabalhoController(JTextField tfTrabalhoCodigo, JTextField tfTrabalhoTipo, JTextField tfTrabalhoTema,
			JTextField tfTrabalhoArea, JTextField tfTrabalhoSubarea) {
		super();
		this.tfTrabalhoCodigo = tfTrabalhoCodigo;
		this.tfTrabalhoTipo = tfTrabalhoTipo;
		this.tfTrabalhoTema = tfTrabalhoTema;
		this.tfTrabalhoArea = tfTrabalhoArea;
		this.tfTrabalhoSubarea = tfTrabalhoSubarea;
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
	}

	private void gravar() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = tfTrabalhoCodigo.getText();
		trabalho.tipo = tfTrabalhoTipo.getText();
		trabalho.tema = tfTrabalhoTema.getText();
		trabalho.area = tfTrabalhoArea.getText();
		trabalho.subarea = tfTrabalhoSubarea.getText();

		if (!trabalho.codigo.equals("") && (!trabalho.tipo.equals("") && trabalho.codigo.matches("[0-9]+"))) {
			gravaTrabalho(trabalho.toString());

			tfTrabalhoCodigo.setText("");
			tfTrabalhoTipo.setText("");
			tfTrabalhoTema.setText("");
			tfTrabalhoArea.setText("");
			tfTrabalhoSubarea.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Um ou mais campos vazios ou possuem caracteres inválidos", "ERRO!",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void gravaTrabalho(String csvTrabalho) throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq = new File(path, "trabalho.csv");
		boolean arqExiste = arq.exists();

		FileWriter fw = new FileWriter(arq, arqExiste);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvTrabalho + System.getProperty("line.separator"));
		pw.flush();
		pw.close();
		fw.close();

	}

	public static void gerarListTrabalho(JTextArea taTrabalhoLista) {
		try {
			String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
			File arq = new File(path, "trabalho.csv");
			if (arq.exists()) {

				BufferedReader reader = new BufferedReader(new FileReader(arq));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.replace(";", " - ");
					taTrabalhoLista.append(line + "\n");
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void atualizarListTrabalho(JTextArea taTrabalhoLista) {
		taTrabalhoLista.setText("");
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "trabalho.csv");
		if (arq.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(arq));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.replace(";", " - ");
					taTrabalhoLista.append(line + "\n");
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
