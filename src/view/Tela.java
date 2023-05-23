package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AlunoController;

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
		lblAlunos.setBounds(10, 11, 294, 23);
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
		
		tfAlunoBusca = new JTextField();
		tfAlunoBusca.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfAlunoBusca.setColumns(10);
		tfAlunoBusca.setBounds(292, 9, 294, 30);
		listAluno.add(tfAlunoBusca);
		
		JButton btnBuscaAluno = new JButton("Buscar");
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
		
		JPanel tabTrabalho = new JPanel();
		tabbedPane.addTab("Trabalhos", null, tabTrabalho, "Registro de Trabalhos");
		tabTrabalho.setLayout(null);
		
		JLayeredPane paneTrabalho = new JLayeredPane();
		paneTrabalho.setBounds(10, 11, 759, 511);
		tabTrabalho.add(paneTrabalho);
		paneTrabalho.setLayout(new CardLayout(0, 0));
		
		JPanel listTrabalho = new JPanel();
		paneTrabalho.add(listTrabalho, "name_3402776311595300");
		listTrabalho.setLayout(null);
		
		JPanel formTrabalho = new JPanel();
		paneTrabalho.add(formTrabalho, "name_3402794614775400");
		formTrabalho.setLayout(null);
		
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
		
		
		JButton btnCancelaTrabalho = new JButton("Cancela");
		btnCancelaTrabalho.setBounds(550, 477, 140, 23);
		btnCancelaTrabalho.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneTrabalho, listTrabalho);
			}
		});
		formTrabalho.add(btnCancelaTrabalho);
		
		JLabel lblNovoTrabalho = new JLabel("Novo Trabalho");
		lblNovoTrabalho.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNovoTrabalho.setBounds(10, 11, 291, 23);
		formTrabalho.add(lblNovoTrabalho);
		
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
		
		JButton btnNovarea = new JButton("Nova Área");
		btnNovarea.setBounds(609, 477, 140, 23);
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
		btnCancelaArea.setBounds(550, 477, 140, 23);
		btnCancelaArea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneArea, listArea);
			}
		});
		formArea.add(btnCancelaArea);
		
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
		btnNovaOrientacao.setBounds(609, 477, 140, 23);
		btnNovaOrientacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneOrientacao, formOrientacao);
			}
		});
		listOrientacao.add(btnNovaOrientacao);
		
		JLabel lblNovaOrientacao = new JLabel("Nova Orientação");
		lblNovaOrientacao.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNovaOrientacao.setBounds(10, 11, 248, 23);
		formOrientacao.add(lblNovaOrientacao);
		
		JButton btnCancelaOrientacao = new JButton("Cancela");
		btnCancelaOrientacao.setBounds(550, 477, 140, 23);
		btnCancelaOrientacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneOrientacao, listOrientacao);
			}
		});
		formOrientacao.add(btnCancelaOrientacao);
		
		AlunoController ctrlAluno = new AlunoController(tfAlunoNome, tfAlunoRa, taAlunoLista, tfAlunoBusca);
		
		btnGravarAluno.addActionListener(ctrlAluno);
		btnBuscaAluno.addActionListener(ctrlAluno);
	}
}
