package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AlunoController;
import controller.AreaController;
import controller.TrabalhoController;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField tfAlunoNome;
	private JTextField tfAlunoRa;
	private JTextField tfAlunoBusca;
	private JTextField tfTrabalhoCodigo;
	private JTextField tfTrabalhoTipo;
	private JTextField tfTrabalhoTema;
	private JTextField tfTrabalhoArea;
	private JTextField tfTrabalhoSubarea;
	private JTextField tfSubareas;
	private JTextField tfOrientacaoBusca;
	private JTextField tfTrabalhoOrientacao;
	private JTextField tfDiaOrientacao;
	private JTextField tfMesOrientacao;
	private JTextField tfAnoOrientacao;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void mudarAba(JLayeredPane tab, JPanel panel) {
		tab.removeAll();
		tab.add(panel);
		tab.repaint();
		tab.revalidate();
	}

	public Tela() {
		setTitle("Sistema de TCC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setBounds(0, 0, 784, 561);
		contentPane.add(tabbedPane);
		
		JPanel tabTelaInicial = new JPanel();
		tabbedPane.addTab("Tela Inicial", null, tabTelaInicial, "Tela Inicial");
		
		JPanel tabAluno = new JPanel();
		tabbedPane.addTab("Alunos", null, tabAluno, "Registro de alunos");
		tabAluno.setLayout(null);
		
		JLayeredPane paneAluno = new JLayeredPane();
		paneAluno.setBounds(10, 11, 759, 511);
		tabAluno.add(paneAluno);
		paneAluno.setLayout(new CardLayout(0, 0));
		
		JPanel listAluno = new JPanel();
		paneAluno.add(listAluno, "name_3401672270451300");
		listAluno.setLayout(null);
		
		JPanel formAluno = new JPanel();
		paneAluno.add(formAluno, "name_3401734247750200");
		formAluno.setLayout(null);
		
		JLabel lblAlunos = new JLabel("Alunos");
		lblAlunos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlunos.setBounds(10, 11, 122, 23);
		listAluno.add(lblAlunos);
		
		JButton btnNovoAluno = new JButton("Novo Aluno");
		btnNovoAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovoAluno.setBounds(609, 477, 140, 23);
		btnNovoAluno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneAluno, formAluno);
			}
		});
		listAluno.add(btnNovoAluno);
		
		JScrollPane scrollPaneAluno = new JScrollPane();
		scrollPaneAluno.setBounds(20, 45, 716, 420);
		listAluno.add(scrollPaneAluno);
		
		JTextArea taAlunoLista = new JTextArea();
		scrollPaneAluno.setViewportView(taAlunoLista);
		taAlunoLista.setEditable(false);	
		
		tfAlunoBusca = new JTextField();
		tfAlunoBusca.setToolTipText("Pesquisar por RA");
		tfAlunoBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAlunoBusca.setColumns(10);
		tfAlunoBusca.setBounds(292, 9, 294, 30);
		listAluno.add(tfAlunoBusca);
		
		JButton btnBuscaAluno = new JButton("Buscar");
		btnBuscaAluno.setToolTipText("Pesquisar por RA");
		btnBuscaAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscaAluno.setBounds(596, 13, 140, 23);
		listAluno.add(btnBuscaAluno);
		
		JLabel lblNovoAluno = new JLabel("Novo Aluno");
		lblNovoAluno.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNovoAluno.setBounds(10, 11, 291, 23);
		formAluno.add(lblNovoAluno);
		
		JButton btnCancelaAluno = new JButton("Cancelar");
		btnCancelaAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelaAluno.setBounds(460, 477, 140, 23);
		btnCancelaAluno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneAluno, listAluno);
			}
		});
		formAluno.add(btnCancelaAluno);
		
		JButton btnGravarAluno = new JButton("Gravar");
		btnGravarAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGravarAluno.setBounds(609, 477, 140, 23);
		formAluno.add(btnGravarAluno);
		
		JLabel lblNomeAluno = new JLabel("Nome: ");
		lblNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeAluno.setBounds(50, 70, 100, 30);
		formAluno.add(lblNomeAluno);
		
		JLabel lblRAAluno = new JLabel("RA:");
		lblRAAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRAAluno.setBounds(50, 120, 100, 30);
		formAluno.add(lblRAAluno);
		
		tfAlunoNome = new JTextField();
		tfAlunoNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAlunoNome.setBounds(160, 70, 485, 30);
		formAluno.add(tfAlunoNome);
		tfAlunoNome.setColumns(10);
		
		tfAlunoRa = new JTextField();
		tfAlunoRa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAlunoRa.setColumns(10);
		tfAlunoRa.setBounds(160, 127, 485, 30);
		formAluno.add(tfAlunoRa);
		
		JButton btnUploadAluno = new JButton("Upload por CSV");
		btnUploadAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUploadAluno.setBounds(10, 477, 140, 23);
		formAluno.add(btnUploadAluno);
		
		// Parte de trabalho
		
				JPanel tabTrabalho = new JPanel();
				tabbedPane.addTab("Trabalhos", null, tabTrabalho, "Registro de Trabalhos");
				tabTrabalho.setLayout(null);
				
				
				JLayeredPane paneTrabalho = new JLayeredPane();
				paneTrabalho.setBounds(10, 11, 759, 511);
				tabTrabalho.add(paneTrabalho);
				paneTrabalho.setLayout(new CardLayout(0, 0));
				
				JPanel listTrabalho = new JPanel();
				paneTrabalho.add(listTrabalho, "name_3402776311595900");
				listTrabalho.setLayout(null);
				
				JPanel formTrabalho = new JPanel();
				paneTrabalho.add(formTrabalho, "name_3402794614776000");
				formTrabalho.setLayout(null);
				
				JScrollPane scrollPaneTrabalho = new JScrollPane();
				scrollPaneTrabalho.setBounds(20, 45, 716, 420);
				listTrabalho.add(scrollPaneTrabalho);
				
				final JTextArea taTrabalhoLista = new JTextArea();
				scrollPaneTrabalho.setViewportView(taTrabalhoLista);
				
				TrabalhoController.gerarListTrabalho(taTrabalhoLista);
				
				
				/*
				tfTrabalhoBusca = new JTextField();
				tfTrabalhoBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfTrabalhoBusca.setColumns(10);
				tfTrabalhoBusca.setBounds(292, 9, 294, 30);
				listTrabalho.add(tfTrabalhoBusca);
				
				JButton btnBuscaTrabalho = new JButton("Buscar");
				btnBuscaTrabalho.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnBuscaTrabalho.setBounds(596, 13, 140, 23);
				listTrabalho.add(btnBuscaTrabalho);*/
				
				JLabel lblTrabalhos = new JLabel("Trabalhos");
				lblTrabalhos.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblTrabalhos.setBounds(10, 11, 117, 23);
				listTrabalho.add(lblTrabalhos);
				
				JButton btnNovoTrabalho = new JButton("Novo Trabalho");
				btnNovoTrabalho.setBounds(609, 477, 140, 23);
				btnNovoTrabalho.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mudarAba(paneTrabalho, formTrabalho);
					}
				});
				listTrabalho.add(btnNovoTrabalho);
				
				
				JButton btnCancelaTrabalho = new JButton("Cancelar");
				btnCancelaTrabalho.setBounds(460, 477, 140, 23);
				btnCancelaTrabalho.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mudarAba(paneTrabalho, listTrabalho);
						TrabalhoController.atualizarListTrabalho(taTrabalhoLista);
					}
				});
				formTrabalho.add(btnCancelaTrabalho);
				
				JLabel lblNovoTrabalho = new JLabel("Novo Trabalho");
				lblNovoTrabalho.setFont(new Font("Tahoma", Font.BOLD, 18));
				lblNovoTrabalho.setBounds(10, 11, 291, 23);
				formTrabalho.add(lblNovoTrabalho);
				
				JLabel lblCodigoTrabalho = new JLabel("Código: ");
				lblCodigoTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblCodigoTrabalho.setBounds(50, 70, 100, 30);
				formTrabalho.add(lblCodigoTrabalho);
				
				tfTrabalhoCodigo = new JTextField();
				tfTrabalhoCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfTrabalhoCodigo.setBounds(160, 70, 485, 30);
				formTrabalho.add(tfTrabalhoCodigo);
				tfTrabalhoCodigo.setColumns(10);
				
				JLabel lblTipoTrabalho = new JLabel("Tipo: ");
				lblTipoTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTipoTrabalho.setBounds(50, 120, 100, 30);
				formTrabalho.add(lblTipoTrabalho);
				
				tfTrabalhoTipo = new JTextField();
				tfTrabalhoTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfTrabalhoTipo.setBounds(160, 120, 485, 30);
				formTrabalho.add(tfTrabalhoTipo);
				tfTrabalhoTipo.setColumns(10);
				
				JLabel lblTemaTrabalho = new JLabel("Tema: ");
				lblTemaTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblTemaTrabalho.setBounds(50, 170, 100, 30);
				formTrabalho.add(lblTemaTrabalho);
				
				tfTrabalhoTema = new JTextField();
				tfTrabalhoTema.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfTrabalhoTema.setBounds(160, 170, 485, 30);
				formTrabalho.add(tfTrabalhoTema);
				tfTrabalhoTema.setColumns(10);
				
				JLabel lblAreaTrabalho = new JLabel("Área: ");
				lblAreaTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAreaTrabalho.setBounds(50, 220, 100, 30);
				formTrabalho.add(lblAreaTrabalho);
				
				tfTrabalhoArea = new JTextField();
				tfTrabalhoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfTrabalhoArea.setBounds(160, 220, 485, 30);
				formTrabalho.add(tfTrabalhoArea);
				tfTrabalhoArea.setColumns(10);
				
				JLabel lblSubareaTrabalho = new JLabel("Subárea: ");
				lblSubareaTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblSubareaTrabalho.setBounds(50, 270, 100, 30);
				formTrabalho.add(lblSubareaTrabalho);
				
				tfTrabalhoSubarea = new JTextField();
				tfTrabalhoSubarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfTrabalhoSubarea.setBounds(160, 270, 485, 30);
				formTrabalho.add(tfTrabalhoSubarea);
				tfTrabalhoSubarea.setColumns(10);
				
				JLabel lblAlunosParticipantesTrabalho = new JLabel("Alunos participantes: ");
				lblAlunosParticipantesTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAlunosParticipantesTrabalho.setBounds(50, 320, 130, 30);
				formTrabalho.add(lblAlunosParticipantesTrabalho);
				
				JButton btnUploadTrabalho = new JButton("Upload por CSV");
				btnUploadTrabalho.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnUploadTrabalho.setBounds(10, 477, 140, 23);
				formTrabalho.add(btnUploadTrabalho);
				
				JButton btnGravarTrabalho = new JButton("Gravar");
				btnGravarTrabalho.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnGravarTrabalho.setBounds(609, 477, 140, 23);
				formTrabalho.add(btnGravarTrabalho);
		
		JPanel tabArea = new JPanel();
		tabbedPane.addTab("Áreas", null, tabArea, "Registro de áreas");
		tabArea.setLayout(null);
		
		JLayeredPane paneArea = new JLayeredPane();
		paneArea.setBounds(10, 11, 759, 511);
		tabArea.add(paneArea);
		paneArea.setLayout(new CardLayout(0, 0));
		
		JPanel listArea = new JPanel();
		paneArea.add(listArea, "name_3406425248272500");
		listArea.setLayout(null);
		
		JPanel formArea = new JPanel();
		paneArea.add(formArea, "name_3406428848724000");
		formArea.setLayout(null);
		
		JLabel lblAreas = new JLabel("Áreas");
		lblAreas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAreas.setBounds(10, 11, 117, 23);
		listArea.add(lblAreas);
		
		JTextField tfAreaBusca = new JTextField();
		tfAreaBusca.setToolTipText("Pesquisar por nome de área");
		tfAreaBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAreaBusca.setColumns(10);
		tfAreaBusca.setBounds(291, 11, 294, 30);
		listArea.add(tfAreaBusca);
		
		JButton btnBuscaArea = new JButton("Buscar");
		btnBuscaArea.setToolTipText("Pesquisar por nome de área");
		btnBuscaArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscaArea.setBounds(595, 15, 140, 23);
		listArea.add(btnBuscaArea);
		
		JScrollPane scrollPaneArea = new JScrollPane();
		scrollPaneArea.setBounds(20, 48, 714, 418);
		listArea.add(scrollPaneArea);
		
		JTextArea taAreaLista = new JTextArea();
		scrollPaneArea.setViewportView(taAreaLista);
		
		JButton btnNovarea = new JButton("Nova Área");
		btnNovarea.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovarea.setBounds(595, 477, 140, 23);
		btnNovarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneArea, formArea);
			}
		});
		listArea.add(btnNovarea);
		
		JLabel lblNovaArea = new JLabel("Nova Área");
		lblNovaArea.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNovaArea.setBounds(10, 11, 117, 23);
		formArea.add(lblNovaArea);
		
		JButton btnCancelaArea = new JButton("Cancela");
		btnCancelaArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelaArea.setBounds(460, 477, 140, 23);
		btnCancelaArea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneArea, listArea);
			}
		});
		formArea.add(btnCancelaArea);
		
		JLabel lblCodigoArea = new JLabel("Código: ");
		lblCodigoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoArea.setBounds(50, 70, 100, 30);
		formArea.add(lblCodigoArea);
		
		JLabel lblNomeArea = new JLabel("Nome:");
		lblNomeArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeArea.setBounds(50, 120, 100, 30);
		formArea.add(lblNomeArea);
		
		JLabel lblDescArea = new JLabel("Breve descrição:");
		lblDescArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescArea.setBounds(50, 170, 117, 30);
		formArea.add(lblDescArea);
		
		JTextField tfNomeArea = new JTextField();
		tfNomeArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNomeArea.setColumns(10);
		tfNomeArea.setBounds(160, 120, 485, 30);
		formArea.add(tfNomeArea);
		
		JTextField tfCodigoArea = new JTextField();
		tfCodigoArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCodigoArea.setColumns(10);
		tfCodigoArea.setBounds(160, 70, 485, 30);
		formArea.add(tfCodigoArea);
		
		JTextArea taDescArea = new JTextArea();
		taDescArea.setBounds(160, 175, 485, 82);
		formArea.add(taDescArea);
		
		JButton btnGravaArea = new JButton("Gravar");
		btnGravaArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGravaArea.setBounds(609, 477, 140, 23);
		formArea.add(btnGravaArea);
		
		JLabel lblSubareas = new JLabel("Subáreas:");
		lblSubareas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSubareas.setBounds(50, 268, 117, 30);
		formArea.add(lblSubareas);
		
		tfSubareas = new JTextField();
		tfSubareas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSubareas.setColumns(10);
		tfSubareas.setBounds(160, 275, 335, 30);
		formArea.add(tfSubareas);
		
		JButton btnAdicionaSubarea = new JButton("Adicionar");
		btnAdicionaSubarea.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdicionaSubarea.setBounds(505, 279, 140, 23);
		formArea.add(btnAdicionaSubarea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 316, 485, 82);
		formArea.add(scrollPane);
		
		JTextArea taSubareas = new JTextArea();
		scrollPane.setViewportView(taSubareas);
		taSubareas.setEditable(false);
		
		JPanel tabOrientacao = new JPanel();
		tabbedPane.addTab("Orientações", null, tabOrientacao, "Registro de Orientações");
		tabOrientacao.setLayout(null);
		
		JLayeredPane paneOrientacao = new JLayeredPane();
		paneOrientacao.setBounds(10, 11, 759, 511);
		tabOrientacao.add(paneOrientacao);
		paneOrientacao.setLayout(new CardLayout(0, 0));
		
		JPanel listOrientacao = new JPanel();
		paneOrientacao.add(listOrientacao, "name_3406619688295500");
		listOrientacao.setLayout(null);
		
		JPanel formOrientacao = new JPanel();
		paneOrientacao.add(formOrientacao, "name_3406630470810300");
		formOrientacao.setLayout(null);
		
		JLabel lblOrientacoes = new JLabel("Orientações");
		lblOrientacoes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrientacoes.setBounds(10, 11, 117, 23);
		listOrientacao.add(lblOrientacoes);
		
		JButton btnNovaOrientacao = new JButton("Nova Orientação");
		btnNovaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNovaOrientacao.setBounds(594, 476, 140, 23);
		btnNovaOrientacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneOrientacao, formOrientacao);
			}
		});
		listOrientacao.add(btnNovaOrientacao);
		
		JButton btnLimpaBuscaOrientacao = new JButton("Limpar Busca");
		btnLimpaBuscaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpaBuscaOrientacao.setBounds(140, 15, 140, 23);
		listOrientacao.add(btnLimpaBuscaOrientacao);
		
		JScrollPane scrollPaneOrientacao = new JScrollPane();
		scrollPaneOrientacao.setBounds(20, 49, 712, 416);
		listOrientacao.add(scrollPaneOrientacao);
		
		JTextArea taOrientacaoLista = new JTextArea();
		scrollPaneOrientacao.setViewportView(taOrientacaoLista);
		
		tfOrientacaoBusca = new JTextField();
		tfOrientacaoBusca.setToolTipText("Pesquisar por nome de área");
		tfOrientacaoBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfOrientacaoBusca.setColumns(10);
		tfOrientacaoBusca.setBounds(290, 11, 294, 30);
		listOrientacao.add(tfOrientacaoBusca);
		
		JButton btnBuscaOrientacao = new JButton("Buscar");
		btnBuscaOrientacao.setToolTipText("Pesquisar por nome de área");
		btnBuscaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscaOrientacao.setBounds(594, 15, 140, 23);
		listOrientacao.add(btnBuscaOrientacao);
		
		JLabel lblNovaOrientacao = new JLabel("Nova Orientação");
		lblNovaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNovaOrientacao.setBounds(10, 11, 248, 23);
		formOrientacao.add(lblNovaOrientacao);
		
		JButton btnCancelaOrientacao = new JButton("Cancela");
		btnCancelaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelaOrientacao.setBounds(460, 477, 140, 23);
		btnCancelaOrientacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneOrientacao, listOrientacao);
			}
		});
		formOrientacao.add(btnCancelaOrientacao);
		
		JButton btnGravaOrientacao = new JButton("Gravar");
		btnGravaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGravaOrientacao.setBounds(609, 477, 140, 23);
		formOrientacao.add(btnGravaOrientacao);
		
		JLabel lblTrabalho = new JLabel("Trabalho:");
		lblTrabalho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrabalho.setBounds(50, 70, 100, 30);
		formOrientacao.add(lblTrabalho);
		
		tfTrabalhoOrientacao = new JTextField();
		tfTrabalhoOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTrabalhoOrientacao.setColumns(10);
		tfTrabalhoOrientacao.setBounds(160, 70, 248, 30);
		formOrientacao.add(tfTrabalhoOrientacao);
		
		JButton btnBuscarTrabalho = new JButton("Buscar");
		btnBuscarTrabalho.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscarTrabalho.setBounds(418, 75, 140, 23);
		formOrientacao.add(btnBuscarTrabalho);
		
		JLabel lblOrientaoAssociada = new JLabel("Orientação associada à:");
		lblOrientaoAssociada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrientaoAssociada.setBounds(60, 111, 292, 30);
		formOrientacao.add(lblOrientaoAssociada);
		
		JLabel lblDataOrientacao = new JLabel("Data:");
		lblDataOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataOrientacao.setBounds(50, 152, 100, 30);
		formOrientacao.add(lblDataOrientacao);
		
		tfDiaOrientacao = new JTextField();
		tfDiaOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDiaOrientacao.setColumns(10);
		tfDiaOrientacao.setBounds(160, 152, 55, 30);
		formOrientacao.add(tfDiaOrientacao);
		
		JLabel lblBarra1 = new JLabel("/");
		lblBarra1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBarra1.setBounds(225, 152, 13, 30);
		formOrientacao.add(lblBarra1);
		
		tfMesOrientacao = new JTextField();
		tfMesOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfMesOrientacao.setColumns(10);
		tfMesOrientacao.setBounds(236, 152, 55, 30);
		formOrientacao.add(tfMesOrientacao);
		
		JLabel lblBarra2 = new JLabel("/");
		lblBarra2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBarra2.setBounds(301, 152, 24, 30);
		formOrientacao.add(lblBarra2);
		
		tfAnoOrientacao = new JTextField();
		tfAnoOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAnoOrientacao.setColumns(10);
		tfAnoOrientacao.setBounds(312, 152, 96, 30);
		formOrientacao.add(tfAnoOrientacao);
		
		JLabel lblPontosOrientacao = new JLabel("Pontos:");
		lblPontosOrientacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPontosOrientacao.setBounds(50, 205, 100, 30);
		formOrientacao.add(lblPontosOrientacao);
		
		JTextArea taPontosOrientacao = new JTextArea();
		taPontosOrientacao.setBounds(160, 210, 398, 194);
		formOrientacao.add(taPontosOrientacao);
		
		AlunoController ctrlAluno = new AlunoController(tfAlunoNome, tfAlunoRa, taAlunoLista, tfAlunoBusca);
		
		JButton btnLimpaBuscaAluno = new JButton("Limpar Busca");
		btnLimpaBuscaAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpaBuscaAluno.setBounds(142, 13, 140, 23);
		listAluno.add(btnLimpaBuscaAluno);
		
		btnGravarAluno.addActionListener(ctrlAluno);
		btnBuscaAluno.addActionListener(ctrlAluno);
		btnUploadAluno.addActionListener(ctrlAluno);
		btnLimpaBuscaAluno.addActionListener(ctrlAluno);
		
		TrabalhoController ctrlTrabalho = new TrabalhoController(tfTrabalhoCodigo, tfTrabalhoTipo, 
		tfTrabalhoTema, tfTrabalhoArea,  tfTrabalhoSubarea);
		
		btnGravarTrabalho.addActionListener(ctrlTrabalho);
		btnUploadTrabalho.addActionListener(ctrlTrabalho);
		
		AreaController ctrlArea = new AreaController(tfCodigoArea, tfNomeArea, taDescArea, tfSubareas, taSubareas, taAreaLista, tfAreaBusca);
		
		JButton btnLimpaBuscaArea = new JButton("Limpar Busca");
		btnLimpaBuscaArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpaBuscaArea.setBounds(141, 15, 140, 23);
		listArea.add(btnLimpaBuscaArea);
		
		btnGravaArea.addActionListener(ctrlArea);
		btnAdicionaSubarea.addActionListener(ctrlArea);
		btnBuscaArea.addActionListener(ctrlArea);
		btnLimpaBuscaArea.addActionListener(ctrlArea);
	}
}
