package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

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
		
		JLabel lblNewLabel = new JLabel("Alunos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 294, 23);
		listAluno.add(lblNewLabel);
		
		JButton btnNovoAluno = new JButton("Novo Aluno");
		btnNovoAluno.setBounds(609, 477, 140, 23);
		btnNovoAluno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneAluno, formAluno);
			}
		});
		listAluno.add(btnNovoAluno);
		
		JLabel lblNewLabel_1 = new JLabel("Novo Aluno");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 291, 23);
		formAluno.add(lblNewLabel_1);
		
		JButton btnCancelaAluno = new JButton("Cancelar");
		btnCancelaAluno.setBounds(550, 477, 140, 23);
		btnCancelaAluno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneAluno, listAluno);
			}
		});
		formAluno.add(btnCancelaAluno);
		
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
		
		JLabel lblNewLabel_2 = new JLabel("Trabalhos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 11, 117, 23);
		listTrabalho.add(lblNewLabel_2);
		
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Novo Trabalho");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 11, 291, 23);
		formTrabalho.add(lblNewLabel_1_1);
		
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
		
		JLabel lblNewLabel_2_1 = new JLabel("Áreas");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(10, 11, 117, 23);
		listArea.add(lblNewLabel_2_1);
		
		JButton btnNovarea = new JButton("Nova Área");
		btnNovarea.setBounds(609, 477, 140, 23);
		btnNovarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneArea, formArea);
			}
		});
		listArea.add(btnNovarea);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Nova Área");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1_1.setBounds(10, 11, 117, 23);
		formArea.add(lblNewLabel_2_1_1);
		
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
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Orientações");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1_2.setBounds(10, 11, 117, 23);
		listOrientacao.add(lblNewLabel_2_1_2);
		
		JButton btnNovaOrientacao = new JButton("Nova Orientação");
		btnNovaOrientacao.setBounds(609, 477, 140, 23);
		btnNovaOrientacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneOrientacao, formOrientacao);
			}
		});
		listOrientacao.add(btnNovaOrientacao);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Nova Orientação");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1_1_1.setBounds(10, 11, 117, 23);
		formOrientacao.add(lblNewLabel_2_1_1_1);
		
		JButton btnCancelaOrientacao = new JButton("Cancela");
		btnCancelaOrientacao.setBounds(550, 477, 140, 23);
		btnCancelaOrientacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mudarAba(paneOrientacao, listOrientacao);
			}
		});
		formOrientacao.add(btnCancelaOrientacao);
	}
}
