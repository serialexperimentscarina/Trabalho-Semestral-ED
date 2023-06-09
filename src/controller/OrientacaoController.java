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

import model.Orientacao;
import model.Trabalho;

public class OrientacaoController implements ActionListener{
	
	private JTextField tfOrientacaoBusca;
	private JTextArea taOrientacaoLista;
	private JTextField tfTrabalhoOrientacao;
	private JLabel lblOrientacaoTrabalho;
	private JTextField tfDiaOrientacao;
	private JTextField tfMesOrientacao;
	private JTextField tfAnoOrientacao;
	private JTextArea taPontosOrientacao;
	
	public OrientacaoController(JTextField tfOrientacaoBusca, JTextArea taOrientacaoLista, JTextField tfTrabalhoOrientacao,
			JLabel lblOrientacaoTrabalho, JTextField tfDiaOrientacao, JTextField tfMesOrientacao, JTextField tfAnoOrientacao, 
			JTextArea taPontosOrientacao) {
		this.tfOrientacaoBusca = tfOrientacaoBusca;
		this.taOrientacaoLista = taOrientacaoLista;
		this.tfTrabalhoOrientacao = tfTrabalhoOrientacao;
		this.lblOrientacaoTrabalho = lblOrientacaoTrabalho;
		this.tfDiaOrientacao = tfDiaOrientacao;
		this.tfMesOrientacao = tfMesOrientacao;
		this.tfAnoOrientacao = tfAnoOrientacao;
		this.taPontosOrientacao = taPontosOrientacao;
		
		try {
			InicializaPilhas();
			atualizaLista();
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
				case "Excluir última":
					excluir();
					break;
				case "Buscar última":
					buscar();	
					break;
				case "Adicionar":
					adicionar();
					break;
				case "Limpar Busca":
					atualizaLista();
					break;
				default:
					break;
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a execução do programa", "ERRO!", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	private void gravar() throws Exception {
		Orientacao orientacao = new Orientacao();
		
		String dia = tfDiaOrientacao.getText().toString();
		String mes = tfMesOrientacao.getText().toString();
		String ano = tfAnoOrientacao.getText().toString();
		
		if ((dia.equals("") || !dia.matches("[0-9]+") || Integer.valueOf(dia) < 1 || Integer.valueOf(dia) > 31) ||
			(mes.equals("") || !mes.matches("[0-9]+") || Integer.valueOf(mes) < 1 || Integer.valueOf(mes) > 12)	||
			(ano.equals("") || !ano.matches("[0-9]+") || ano.length() > 4 || ano.length() < 4 )) {
			JOptionPane.showMessageDialog(null, "Formato de data vazio ou inválido", "ERRO", JOptionPane.ERROR_MESSAGE);
			return;
		}
		orientacao.dia = Integer.parseInt(dia);
		orientacao.mes = Integer.parseInt(mes);
		orientacao.ano = Integer.parseInt(ano);
		
		orientacao.pontos = taPontosOrientacao.getText().toString();
		if (orientacao.pontos.equals("")) {
			JOptionPane.showMessageDialog(null, "Nenhum ponto foi definido para a orientação", "ERRO", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (lblOrientacaoTrabalho.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Orientação não foi associada à um trabalho!", "ERRO", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = Integer.parseInt(lblOrientacaoTrabalho.getText());
		TrabalhoController.tabelaEspalhamentoGrupoCodigo.busca(trabalho).orientacoes.push(orientacao);
		
		gravaOrientacao(orientacao.toString() + ";" + trabalho.codigo);
		atualizaLista();
		tfDiaOrientacao.setText("");
		tfMesOrientacao.setText("");
		tfAnoOrientacao.setText("");
		taPontosOrientacao.setText("");
		tfTrabalhoOrientacao.setText("");
		JOptionPane.showMessageDialog(null, "Orientação adicionada com sucesso!");
	}

	private void gravaOrientacao(String csvOrientacao) throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq = new File(path, "orientacoes.csv");
		boolean arqExiste = arq.exists();

		FileWriter fw = new FileWriter(arq, arqExiste);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvOrientacao + System.getProperty("line.separator"));
		pw.flush();
		pw.close();
		fw.close();
	}
	
	private void excluir() throws Exception {
		if (tfOrientacaoBusca.getText().equals("") || !tfOrientacaoBusca.getText().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Código inválido!", "ERRO!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = Integer.parseInt(tfOrientacaoBusca.getText());
		trabalho = TrabalhoController.tabelaEspalhamentoGrupoCodigo.busca(trabalho);
		
		if (trabalho != null && !trabalho.orientacoes.isEmpty()) {
			Orientacao orientacao = (Orientacao) trabalho.orientacoes.pop();
			JOptionPane.showMessageDialog(null, "Última orientação removida com sucesso");
			excluirOrientacao(trabalho.codigo, orientacao);
		} else {
			JOptionPane.showMessageDialog(null, "Trabalho não existe ou não possui orientações", "ERRO!", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void excluirOrientacao(int codigo, Orientacao orientacao) throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "orientacoes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bufferR = new BufferedReader(isr);
			
			File novoArq = new File(path, "temp.csv");
			StringBuffer bufferW = new StringBuffer();
			FileWriter fWriter = new FileWriter(novoArq);
			PrintWriter pWriter = new PrintWriter(fWriter);
			
			String linha = bufferR.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (codigo != Integer.parseInt(vetLinha[4]) || orientacao.dia != Integer.parseInt(vetLinha[0]) || orientacao.mes != Integer.parseInt(vetLinha[1]) || orientacao.ano != Integer.parseInt(vetLinha[2])) {
					bufferW.append(linha + System.getProperty("line.separator"));
				} 
				linha = bufferR.readLine();
			}
			
			bufferR.close();
			isr.close();
			fis.close();
			pWriter.write(bufferW.toString());
			pWriter.flush();
			pWriter.close();
			fWriter.close();
			
			arq.delete();
			novoArq.renameTo(arq);
			atualizaLista();
		}
	}


	private void adicionar() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = Integer.parseInt(tfTrabalhoOrientacao.getText());
		trabalho = TrabalhoController.tabelaEspalhamentoGrupoCodigo.busca(trabalho);
		if (trabalho != null) {
			lblOrientacaoTrabalho.setText(String.valueOf(trabalho.codigo));
			tfTrabalhoOrientacao.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Trabalho com o código informado não encontrado no sistema.");

		}
	}
	
	private void buscar() throws Exception {
		Trabalho trabalho = new Trabalho();
		trabalho.codigo = Integer.parseInt(tfOrientacaoBusca.getText());
		trabalho = TrabalhoController.tabelaEspalhamentoGrupoCodigo.busca(trabalho);
		if (trabalho != null) {
			try {
				Orientacao orientacaoMaisRecente = (Orientacao) trabalho.orientacoes.top();
				taOrientacaoLista.setText("Trabalho #" + trabalho.codigo + " (" + orientacaoMaisRecente.dia + "/" + orientacaoMaisRecente.mes + "/" + orientacaoMaisRecente.ano + ") - " + orientacaoMaisRecente.pontos + System.getProperty("line.separator"));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Trabalho com o código informado não possui encontros");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Trabalho com o código informado não encontrado no sistema.");

		}
	}

	private void InicializaPilhas() throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "orientacoes.csv");

		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				Orientacao orientacao = new Orientacao();
				orientacao.dia = Integer.parseInt(vetLinha[0]);
				orientacao.mes = Integer.parseInt(vetLinha[1]);
				orientacao.ano = Integer.parseInt(vetLinha[2]);
				orientacao.pontos = vetLinha[3];
				Trabalho trabalho = new Trabalho();
				trabalho.codigo = Integer.parseInt(vetLinha[4]);
				trabalho = TrabalhoController.tabelaEspalhamentoGrupoCodigo.busca(trabalho);
				
				if (trabalho != null) {
					trabalho.orientacoes.push(orientacao);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}
	
	private void atualizaLista() throws Exception {
		String path = (System.getProperty("user.home") + File.separator + "SistemaTCC");
		File arq = new File(path, "orientacoes.csv");

		taOrientacaoLista.setText("");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				taOrientacaoLista.append("Trabalho #" + vetLinha[4] + " (" + vetLinha[0] + "/" + vetLinha[1] + "/" + vetLinha[2] + ") - " + vetLinha[3] + System.getProperty("line.separator"));
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
		}
	}
}
