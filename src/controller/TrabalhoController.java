package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Aluno;
import model.Trabalho;

public class TrabalhoController implements ActionListener {

	private JTextField tfTrabalhoCodigo;
	private JTextField tfTrabalhoTipo;
	private JTextField tfTrabalhoTema;
	private JTextField tfTrabalhoArea;
	private JTextField tfTrabalhoSubarea;
	private JLabel lblBuscaIntegrante;
	private JTextField tfTrabalhoBusca;
	private JTextArea taTrabalhoLista;
	
	private TabelaGrupoCodigoController tabelaCodigo;
	private TabelaGrupoSubareaController tabelaSubarea;
	

	public TrabalhoController(JTextField tfTrabalhoCodigo, JTextField tfTrabalhoTipo, JTextField tfTrabalhoTema,
			JTextField tfTrabalhoArea, JTextField tfTrabalhoSubarea, JLabel lblBuscaIntegrante, JTextField tfTrabalhoBusca
			, JTextArea taTrabalhoLista) {
		super();
		this.tfTrabalhoCodigo = tfTrabalhoCodigo;
		this.tfTrabalhoTipo = tfTrabalhoTipo;
		this.tfTrabalhoTema = tfTrabalhoTema;
		this.tfTrabalhoArea = tfTrabalhoArea;
		this.tfTrabalhoSubarea = tfTrabalhoSubarea;
		this.lblBuscaIntegrante = lblBuscaIntegrante;
		this.tfTrabalhoBusca = tfTrabalhoBusca;
		this.taTrabalhoLista = taTrabalhoLista;
	
		
		tabelaCodigo = new TabelaGrupoCodigoController();
		tabelaSubarea = new TabelaGrupoSubareaController();
		try {
			populaTabelas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
				case "Limpar Busca":
					gerarListTrabalho(taTrabalhoLista);;	
					break;
				default:
					break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void buscar() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = tfTrabalhoBusca.getText();
		
		trabalho = buscaTrabalho(trabalho);
		if (trabalho.tipo != null) {
			taTrabalhoLista.setText(trabalho.codigo + ";" + trabalho.tipo + ";" + trabalho.tema + ";" + trabalho.area + ";" + trabalho.subarea + ";" + trabalho.integrantes);
		} else {
			JOptionPane.showMessageDialog(null, "Trabalho não encontrado!", "ERRO!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Trabalho buscaTrabalho(Trabalho trabalho) throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "trabalho.csv");
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(trabalho.codigo)) {
					trabalho.codigo = vetLinha[0];
					trabalho.tipo = vetLinha[1];
					trabalho.tema = vetLinha[2];
					trabalho.area = vetLinha[3];
					trabalho.subarea = vetLinha[4];
					trabalho.integrantes = vetLinha[5];
					break;
				}
				
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return trabalho;
	}	

	private void gravar() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = tfTrabalhoCodigo.getText();
		trabalho.tipo = tfTrabalhoTipo.getText();
		trabalho.tema = tfTrabalhoTema.getText();
		trabalho.area = tfTrabalhoArea.getText();
		trabalho.subarea = tfTrabalhoSubarea.getText();
		trabalho.integrantes = lblBuscaIntegrante.getText();
		
		// Terminar checagem dos campos
		if (!trabalho.codigo.equals("") && (!trabalho.tipo.equals("") && trabalho.codigo.matches("[0-9]+"))) {
			gravaTrabalho(trabalho.toString());
			tfTrabalhoCodigo.setText("");
			tfTrabalhoTipo.setText("");
			tfTrabalhoTema.setText("");
			tfTrabalhoArea.setText("");
			tfTrabalhoSubarea.setText("");
			lblBuscaIntegrante.setText("");
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
		taTrabalhoLista.setText("");
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
	
	private void upload() throws Exception {
		UploadController uploadCrtl = new UploadController();
		File arquivo = uploadCrtl.uploadArquivo();
		ListaObject listaTrabalho = new ListaObject();
		
		if (arquivo != null) {
			FileInputStream fInStr = new FileInputStream(arquivo);
			InputStreamReader InStrReader = new InputStreamReader(fInStr);
			BufferedReader bufferReader = new BufferedReader(InStrReader);
			String linha = bufferReader.readLine();
			
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].matches("[0-9]+") &&
					(!vetLinha[1].equals("")) &&
					(!vetLinha[2].equals("")) &&
					(!vetLinha[3].equals("")) &&
					(!vetLinha[4].equals(""))) {
					Trabalho trabalho = new Trabalho();
					trabalho.codigo = vetLinha[0];
					trabalho.tipo = vetLinha[1];
					trabalho.tema = vetLinha[2];
					trabalho.area = vetLinha[3];
					trabalho.subarea = vetLinha[4];
					listaTrabalho.addFirst(trabalho);
					tabelaCodigo.adiciona(trabalho);
					tabelaSubarea.adiciona(trabalho);
				} else {
					JOptionPane.showMessageDialog(null, "Um ou mais campos inválidos passados por CSV, verifique seu arquivo e tente novamente", "ERRO!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();
			
			
			while (!listaTrabalho.isEmpty()) {
				gravaTrabalho(listaTrabalho.get(0).toString());
				listaTrabalho.removeFirst();
			}
			
			JOptionPane.showMessageDialog(null, "Upload feito com sucesso", "Upload concluído", JOptionPane.PLAIN_MESSAGE);
			
		}
	}
	
	private void populaTabelas() throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "trabalho.csv");
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Trabalho trabalho = new Trabalho();
				trabalho.codigo = vetLinha[0];
				trabalho.tipo = vetLinha[1];
				trabalho.tema = vetLinha[2];
				trabalho.area = vetLinha[3];
				trabalho.subarea = vetLinha[4];
				trabalho.integrantes = vetLinha[5];
				tabelaCodigo.adiciona(trabalho);
				tabelaSubarea.adiciona(trabalho);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}
	
	public static void buscarAluno(JTextField tfBuscaAluno, JLabel lblBuscaIntegrante) {
	    String nomeAluno = tfBuscaAluno.getText();
	    String path = System.getProperty("user.home") + File.separator + "SistemaTCC" + File.separator + "aluno.csv";
	    StringBuilder listaDeNomes = new StringBuilder(lblBuscaIntegrante.getText());
	    
	    try {
	    	FileReader fileReader = new FileReader(path);
	    	BufferedReader reader = new BufferedReader(fileReader);
	        String line;

	        while ((line = reader.readLine()) != null) {
	            if (line.contains(nomeAluno)) {
	                if (!lblBuscaIntegrante.getText().contains(nomeAluno)) {
	                    if (listaDeNomes.length() > 0) {
	                    	listaDeNomes.append(", ");
	                    }
	                    listaDeNomes.append(nomeAluno);
	                    if (listaDeNomes.length() > 0) {
	                    	lblBuscaIntegrante.setText(listaDeNomes.toString());
	                    	System.out.println(listaDeNomes);
	                    	JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso.");
	                    } else {
	                    	JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado com esse nome.");
	                    }
	                } else {
		            	JOptionPane.showMessageDialog(null, "Aluno já está adicionado neste trabalho.");
		            }
	            } 
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void removerAluno(String nomeAluno, JLabel lblBuscaIntegrante) {
	    String listaDeNomes = lblBuscaIntegrante.getText();
	    StringBuilder listaDeNomesAtualizada = new StringBuilder();

	    if (listaDeNomes.contains(nomeAluno)) {
	        String[] nome = listaDeNomes.split(", ");
	        for (String nomes : nome) {
	            if (!nomes.equals(nomeAluno)) {
	                if (listaDeNomesAtualizada.length() > 0) {
	                	listaDeNomesAtualizada.append(", ");
	                } else {
	                	JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
	                }
	                listaDeNomesAtualizada.append(nomes);
	            } else {
	            	lblBuscaIntegrante.setText(listaDeNomesAtualizada.toString());
	    	        JOptionPane.showMessageDialog(null, "Aluno removido com sucesso.");
	            }
	        }
	    }
	}

}
