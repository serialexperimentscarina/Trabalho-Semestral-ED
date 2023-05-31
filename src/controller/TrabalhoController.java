package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.serialexperimentscarina.listaobject.ListaObject;
import model.Aluno;
import model.Area;
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
	private JTextField tfBuscaIntegrante;
	
	public static TabelaGrupoCodigoController tabelaEspalhamentoGrupoCodigo;
	public static TabelaGrupoSubareaController tabelaEspalhamentoGrupoSubarea;

	private static int numIntegrantes;

	public TrabalhoController(JTextField tfTrabalhoCodigo, JTextField tfTrabalhoTipo, JTextField tfTrabalhoTema,
			JTextField tfTrabalhoArea, JTextField tfTrabalhoSubarea, JLabel lblBuscaIntegrante,
			JTextField tfTrabalhoBusca, JTextArea taTrabalhoLista, JTextField tfBuscaIntegrante) {
		this.tfTrabalhoCodigo = tfTrabalhoCodigo;
		this.tfTrabalhoTipo = tfTrabalhoTipo;
		this.tfTrabalhoTema = tfTrabalhoTema;
		this.tfTrabalhoArea = tfTrabalhoArea;
		this.tfTrabalhoSubarea = tfTrabalhoSubarea;
		this.lblBuscaIntegrante = lblBuscaIntegrante;
		this.tfTrabalhoBusca = tfTrabalhoBusca;
		this.taTrabalhoLista = taTrabalhoLista;
		this.tfBuscaIntegrante = tfBuscaIntegrante;
	
		numIntegrantes = 0;
		
		tabelaEspalhamentoGrupoCodigo = new TabelaGrupoCodigoController();
		tabelaEspalhamentoGrupoSubarea = new TabelaGrupoSubareaController();
		try {
			populaTabelas();
			gerarListTrabalho();
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
				case "Buscar por código":
					buscarCodigo();	
					break;
				case "Buscar por subárea":
					buscarArea();	
					break;
				case "Upload por CSV":
					upload();	
					break;
				case "Limpar Busca":
					gerarListTrabalho();
					break;
				case "Adicionar":
					adicionar();	
					break;
				case "Remover":
					remover();	
					break;
				default:
					break;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	private void adicionar() throws Exception {
		Aluno aluno = new Aluno();
		if (tfBuscaIntegrante.getText().toString().equals("") || !tfBuscaIntegrante.getText().toString().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Número de RA do aluno inválido");
			return;
		}
		
		aluno.ra = tfBuscaIntegrante.getText();
		aluno = AlunoController.tabelaEspalhamentoAluno.busca(aluno);
		
		
		if (numIntegrantes < 4) {
			if (aluno != null) {
				String integrantes = lblBuscaIntegrante.getText().toString();
				
				if(integrantes.contains(aluno.nome)) {
					JOptionPane.showMessageDialog(null, "Aluno já está adicionado neste trabalho.");
					return;
				}
				
				numIntegrantes++;
				if (integrantes.equals("")) {
					lblBuscaIntegrante.setText(aluno.nome);
				} else {
					lblBuscaIntegrante.setText(lblBuscaIntegrante.getText().toString() + ", " + aluno.nome);
				}
				JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso.");
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado com esse RA.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Grupo está com o limite de integrantes!");
		}
	}
	
	private void remover() throws Exception {
		if (tfBuscaIntegrante.getText().toString().equals("") || !tfBuscaIntegrante.getText().toString().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Número de RA do aluno inválido");
			return;
		}
		
		Aluno aluno = new Aluno();
		aluno.ra = tfBuscaIntegrante.getText();
		aluno = AlunoController.tabelaEspalhamentoAluno.busca(aluno);
		String listaDeNomes = lblBuscaIntegrante.getText();
		StringBuilder listaDeNomesAtualizada = new StringBuilder();

		if (listaDeNomes.contains(aluno.nome)) {
			String[] nomes = listaDeNomes.split(", ");

			for (String nome : nomes) {
				if (nome.equals(aluno.nome)) {
					JOptionPane.showMessageDialog(null, "Aluno removido com sucesso.");
					numIntegrantes--;
				} else {
					if (listaDeNomesAtualizada.length() > 0) {
						listaDeNomesAtualizada.append(", ");
					}
					listaDeNomesAtualizada.append(nome);
				}
			}
			lblBuscaIntegrante.setText(listaDeNomesAtualizada.toString());
		} else {
			JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
		}
	}

	private void buscarCodigo() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = tfTrabalhoBusca.getText();
		
		trabalho = tabelaEspalhamentoGrupoCodigo.busca(trabalho);
		if (trabalho != null) {
			taTrabalhoLista.setText("Código: " + trabalho.codigo + ", Tipo: " + trabalho.tipo + ", Tema: " + trabalho.tema + ", Área:" + trabalho.area + ", Subárea: " + trabalho.subarea + ", Integrantes: " + trabalho.integrantes);
		} else {
			JOptionPane.showMessageDialog(null, "Trabalho não encontrado!", "ERRO!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void buscarArea() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.subarea = tfTrabalhoBusca.getText();
		
		String trabalhos = tabelaEspalhamentoGrupoSubarea.busca(trabalho);
		if (!trabalhos.equals("")) {
			taTrabalhoLista.setText(trabalhos);
		} else {
			JOptionPane.showMessageDialog(null, "Trabalho não encontrado!", "ERRO!", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void gravar() throws Exception {
		if (numIntegrantes >= 2) {
			Trabalho trabalho = new Trabalho();
			trabalho.codigo = tfTrabalhoCodigo.getText();
			trabalho.tipo = tfTrabalhoTipo.getText();
			trabalho.tema = tfTrabalhoTema.getText();
			trabalho.subarea = tfTrabalhoSubarea.getText();
			trabalho.integrantes = lblBuscaIntegrante.getText();
			
			if (trabalho.codigo.equals("") || trabalho.tipo.equals("") || trabalho.codigo.equals("") || !trabalho.codigo.matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Um ou mais campos vazios ou possuem caracteres inválidos", "ERRO!",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Area area = new Area();
			area.nome = tfTrabalhoArea.getText();
			area = AreaController.tabelaEspalhamentoArea.busca(area);
			
			if (area == null) {
				JOptionPane.showMessageDialog(null, "Área não cadastrada no sistema", "ERRO!",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			trabalho.area = area.nome;
			
			boolean subareaExiste = false;
			int totalSubareas = area.subareas.size();
			
			for (int i = 0; i < totalSubareas; i++) {
				if (area.subareas.get(i).equals(trabalho.subarea)) {
					subareaExiste = true;
					break;
				}
			}
			if(!subareaExiste) {
				JOptionPane.showMessageDialog(null, "Subárea não cadastrada no sistema", "ERRO!",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			gravaTrabalho(trabalho.toString());
			tabelaEspalhamentoGrupoCodigo.adiciona(trabalho);
			tabelaEspalhamentoGrupoSubarea.adiciona(trabalho);
			tfTrabalhoCodigo.setText("");
			tfTrabalhoTipo.setText("");
			tfTrabalhoTema.setText("");
			tfTrabalhoArea.setText("");
			tfTrabalhoSubarea.setText("");
			lblBuscaIntegrante.setText("");
			numIntegrantes = 0;
			
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade de integrantes inválida! Mínimo: 2");
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

	public void gerarListTrabalho() throws Exception {
		taTrabalhoLista.setText(tabelaEspalhamentoGrupoCodigo.lista());
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
				if (vetLinha[0].matches("[0-9]+") && (!vetLinha[1].equals("")) && (!vetLinha[2].equals(""))
						&& (!vetLinha[3].equals("")) && (!vetLinha[4].equals(""))) {
					Trabalho trabalho = new Trabalho();
					trabalho.codigo = vetLinha[0];
					trabalho.tipo = vetLinha[1];
					trabalho.tema = vetLinha[2];
					trabalho.area = vetLinha[3];
					trabalho.subarea = vetLinha[4];
					listaTrabalho.addFirst(trabalho);
				} else {
					JOptionPane.showMessageDialog(null,
							"Um ou mais campos inválidos passados por CSV, verifique seu arquivo e tente novamente",
							"ERRO!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();

			while (!listaTrabalho.isEmpty()) {
				Trabalho trabalho = (Trabalho) listaTrabalho.get(0);
				gravaTrabalho(trabalho.toString());
				tabelaEspalhamentoGrupoCodigo.adiciona(trabalho);
				tabelaEspalhamentoGrupoSubarea.adiciona(trabalho);
				listaTrabalho.removeFirst();
			}

			JOptionPane.showMessageDialog(null, "Upload feito com sucesso", "Upload concluído",
					JOptionPane.PLAIN_MESSAGE);

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
				tabelaEspalhamentoGrupoCodigo.adiciona(trabalho);
				tabelaEspalhamentoGrupoSubarea.adiciona(trabalho);
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}
}
