package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.LeitorDAO;
import model.Aluno;
import model.AlunoCursoNota;
import model.Curso;
import model.Nota;

public class Gui extends JFrame {

	// Classe principal da tela do sistema acadêmico

	private static final long serialVersionUID = 1L;

	// Painel principal onde os componentes da janela são colocados
	private JPanel contentPane;

	// Campos usados para mostrar resultados e informações nas abas
	private JTextField tf_Resultado_Busca_Nome;
	private JTextField tf_Resultado_Busca_Curso;
	private JTextField tf_NomeBoletim;
	private JTextField tf_RgmBoletim;
	private JTextField tf_CursoBoletim;
	private JTextField tf_NotaBoletim;
	private JTextField tf_FaltasBoletim;
	private JTextField tf_NotaBancoDadosBoletim;
	private JTextField tf_FaltasBancoDadosBoletim;
	private JTextField tf_NotaProgramacaoWebBoletim;
	private JTextField tf_FaltasProgramacaoWebBoletim;

	// Objetos usados para levar os dados da tela para as classes model e para o DAO
	private Aluno aluno;
	private Curso curso;
	private Nota nota;
	private AlunoCursoNota alunoCursoNota;
	private LeitorDAO dao;

	// Método que inicia a janela do sistema
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da tela: aqui a interface é montada e os botões recebem suas ações
	public Gui() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 379);
		 setResizable(false);

		// Criação da barra de menu superior
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAluno = new JMenu("Aluno e Curso");
		menuBar.add(mnAluno);

		JMenuItem mntmSalvar_Aluno_Curso = new JMenuItem("Salvar");
		mntmSalvar_Aluno_Curso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnAluno.add(mntmSalvar_Aluno_Curso);

		JMenuItem mntmAlterar_Aluno = new JMenuItem("Alterar");
		mnAluno.add(mntmAlterar_Aluno);

		JMenuItem mntmConsultar_Aluno = new JMenuItem("Consultar");
		mnAluno.add(mntmConsultar_Aluno);

		JMenuItem mntmExcluir_Aluno = new JMenuItem("Excluir");
		mnAluno.add(mntmExcluir_Aluno);

		JSeparator separator = new JSeparator();
		mnAluno.add(separator);

		JMenuItem mntmSair_Aluno = new JMenuItem("Sair");
		mntmSair_Aluno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
		mnAluno.add(mntmSair_Aluno);

		JMenu mnNotasFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasFaltas);

		JMenuItem mntmSalvar_Nf = new JMenuItem("Salvar");
		mnNotasFaltas.add(mntmSalvar_Nf);

		JMenuItem mntmAlterar_Nf = new JMenuItem("Alterar");
		mntmAlterar_Nf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mnNotasFaltas.add(mntmAlterar_Nf);

		JMenuItem mntmExcluir_Nf = new JMenuItem("Excluir");
		mnNotasFaltas.add(mntmExcluir_Nf);

		JMenuItem mntmConsultar_Nf = new JMenuItem("Consultar");
		mnNotasFaltas.add(mntmConsultar_Nf);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmSobre_Ajuda = new JMenuItem("Sobre");
		mnAjuda.add(mntmSobre_Ajuda);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Abas principais da tela
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 63, 537, 244);
		contentPane.add(tabbedPane);

		// ===================== ABA DADOS PESSOAIS =====================
		// Nesta aba ficam os dados principais do aluno
		JPanel panel_Dados_Pessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, panel_Dados_Pessoais, null);
		panel_Dados_Pessoais.setLayout(null);

		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setBounds(10, 11, 46, 14);
		panel_Dados_Pessoais.add(lblRgm);

		JFormattedTextField ftf_Rgm = new JFormattedTextField(new MaskFormatter("########"));
		ftf_Rgm.setBounds(48, 8, 103, 20);
		panel_Dados_Pessoais.add(ftf_Rgm);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(185, 11, 46, 14);
		panel_Dados_Pessoais.add(lblNome);

		JFormattedTextField ftf_Nome = new JFormattedTextField();
		ftf_Nome.setBounds(241, 8, 281, 20);
		panel_Dados_Pessoais.add(ftf_Nome);

		JLabel lblDt_Nascmento = new JLabel("Data de Nascimento");
		lblDt_Nascmento.setBounds(10, 52, 103, 14);
		panel_Dados_Pessoais.add(lblDt_Nascmento);

		JFormattedTextField ftf_Dt_Nasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftf_Dt_Nasc.setBounds(113, 49, 118, 20);
		panel_Dados_Pessoais.add(ftf_Dt_Nasc);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(257, 52, 46, 14);
		panel_Dados_Pessoais.add(lblCpf);

		JFormattedTextField ftf_Cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		ftf_Cpf.setBounds(293, 49, 229, 20);
		panel_Dados_Pessoais.add(ftf_Cpf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 98, 46, 14);
		panel_Dados_Pessoais.add(lblEmail);

		JFormattedTextField ftf_Email = new JFormattedTextField();
		ftf_Email.setBounds(48, 95, 474, 20);
		panel_Dados_Pessoais.add(ftf_Email);

		JLabel lblEndereco = new JLabel("End.");
		lblEndereco.setBounds(10, 135, 46, 14);
		panel_Dados_Pessoais.add(lblEndereco);

		JFormattedTextField ftf_Endereco = new JFormattedTextField();
		ftf_Endereco.setBounds(48, 132, 474, 20);
		panel_Dados_Pessoais.add(ftf_Endereco);

		JLabel lblMunicipio = new JLabel("Município");
		lblMunicipio.setBounds(10, 171, 59, 14);
		panel_Dados_Pessoais.add(lblMunicipio);

		JFormattedTextField ftf_Municipio = new JFormattedTextField();
		ftf_Municipio.setBounds(72, 168, 120, 20);
		panel_Dados_Pessoais.add(ftf_Municipio);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(202, 171, 46, 14);
		panel_Dados_Pessoais.add(lblUf);

		JComboBox cb_Uf = new JComboBox();
		cb_Uf.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cb_Uf.setBounds(222, 167, 46, 22);
		panel_Dados_Pessoais.add(cb_Uf);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(278, 171, 46, 14);
		panel_Dados_Pessoais.add(lblCelular);

		JFormattedTextField ftf_Celular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		ftf_Celular.setBounds(330, 168, 192, 20);
		panel_Dados_Pessoais.add(ftf_Celular);

		// ===================== ABA CURSO =====================
		// Nesta aba ficam os dados do curso e período do aluno
		JPanel panel_Curso = new JPanel();
		tabbedPane.addTab("Curso", null, panel_Curso, null);
		panel_Curso.setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(10, 11, 46, 14);
		panel_Curso.add(lblCurso);

		JComboBox cb_Curso = new JComboBox();
		cb_Curso.setModel(new DefaultComboBoxModel(new String[] {"Analise e desenvolvimento de sistemas", "Ciencia da computação"}));
		cb_Curso.setBounds(63, 7, 459, 22);
		panel_Curso.add(cb_Curso);

		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setBounds(10, 42, 59, 14);
		panel_Curso.add(lblCampus);

		JComboBox cb_Campus = new JComboBox();
		cb_Campus.setModel(new DefaultComboBoxModel(new String[] {"Tatuape", "Villa-Lobos", "EAD"}));
		cb_Campus.setBounds(63, 40, 459, 22);
		panel_Curso.add(cb_Campus);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setBounds(10, 88, 46, 14);
		panel_Curso.add(lblPeriodo);

		JRadioButton rdbtn_Matutino = new JRadioButton("Matutino");
		rdbtn_Matutino.setBounds(65, 84, 93, 23);
		panel_Curso.add(rdbtn_Matutino);

		JRadioButton rdbtn_Vespertino = new JRadioButton("Vespertino");
		rdbtn_Vespertino.setBounds(160, 84, 109, 23);
		panel_Curso.add(rdbtn_Vespertino);

		JRadioButton rdbtn_Noturno = new JRadioButton("Noturno");
		rdbtn_Noturno.setBounds(267, 84, 109, 23);
		panel_Curso.add(rdbtn_Noturno);

		// Grupo dos radio buttons do período, para permitir apenas uma opção marcada
		ButtonGroup periodo = new ButtonGroup();
		periodo.add(rdbtn_Matutino);
		periodo.add(rdbtn_Vespertino);
		periodo.add(rdbtn_Noturno);



		// ===================== ABA NOTAS E FALTAS =====================
		// Nesta aba ficam o cadastro, alteração, consulta e exclusão das notas
		JPanel panel_Notas_Faltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, panel_Notas_Faltas, null);
		panel_Notas_Faltas.setLayout(null);

		JLabel lblRGM = new JLabel("RGM");
		lblRGM.setBounds(10, 11, 46, 14);
		panel_Notas_Faltas.add(lblRGM);

		JFormattedTextField ftf_Rgm_pesquisa = new JFormattedTextField(new MaskFormatter("########"));
		ftf_Rgm_pesquisa.setBounds(53, 8, 116, 20);
		panel_Notas_Faltas.add(ftf_Rgm_pesquisa);

		tf_Resultado_Busca_Nome = new JTextField();
		tf_Resultado_Busca_Nome.setBounds(179, 8, 343, 20);
		panel_Notas_Faltas.add(tf_Resultado_Busca_Nome);
		tf_Resultado_Busca_Nome.setColumns(10);
		tf_Resultado_Busca_Nome.setEditable(false);

		tf_Resultado_Busca_Curso = new JTextField();
		tf_Resultado_Busca_Curso.setBounds(10, 37, 512, 20);
		panel_Notas_Faltas.add(tf_Resultado_Busca_Curso);
		tf_Resultado_Busca_Curso.setColumns(10);
		tf_Resultado_Busca_Curso.setEditable(false);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 73, 64, 14);
		panel_Notas_Faltas.add(lblDisciplina);

		JComboBox cb_Disciplina = new JComboBox();
		cb_Disciplina.setModel(new DefaultComboBoxModel(new String[] {"Programação Orientada a Objeto", "Banco de dados", "Programação WEB"}));
		cb_Disciplina.setBounds(72, 69, 450, 22);
		panel_Notas_Faltas.add(cb_Disciplina);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(10, 105, 64, 14);
		panel_Notas_Faltas.add(lblSemestre);

		JComboBox cb_Semestre = new JComboBox();
		cb_Semestre.setModel(new DefaultComboBoxModel(new String[] {"1º", "2º", "3º", "4º", "5º", "6º", "7º", "8º"}));
		cb_Semestre.setBounds(72, 101, 64, 22);
		panel_Notas_Faltas.add(cb_Semestre);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(146, 105, 46, 14);
		panel_Notas_Faltas.add(lblNota);

		JComboBox cb_Nota = new JComboBox();
		cb_Nota.setModel(new DefaultComboBoxModel(new String[] {"0", "0,5", "1", "1,5", "2", "2,5", "3", "3,5", "4", "4,5", "5", "5,5", "6", "6,5", "7", "7,5", "8", "8,5", "9", "9,5", "10"}));
		cb_Nota.setBounds(179, 102, 46, 22);
		panel_Notas_Faltas.add(cb_Nota);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(235, 106, 46, 14);
		panel_Notas_Faltas.add(lblFaltas);

		JFormattedTextField ftf_Faltas = new JFormattedTextField();
		ftf_Faltas.setBounds(277, 102, 81, 20);
		panel_Notas_Faltas.add(ftf_Faltas);


		// ===================== ABA BOLETIM =====================
		// Nesta aba o boletim é montado a partir do RGM e do semestre escolhido
		JPanel panel_Boletim = new JPanel();
		tabbedPane.addTab("Boletim", null, panel_Boletim, null);
		panel_Boletim.setLayout(null);

		JLabel lblNomeBoletim = new JLabel("Nome ");
		lblNomeBoletim.setBounds(183, 11, 46, 14);
		panel_Boletim.add(lblNomeBoletim);

		tf_NomeBoletim = new JTextField();
		tf_NomeBoletim.setBounds(226, 8, 296, 20);
		panel_Boletim.add(tf_NomeBoletim);
		tf_NomeBoletim.setColumns(10);
		tf_NomeBoletim.setEditable(false);

		JLabel lblRgmBoletim = new JLabel("RGM");
		lblRgmBoletim.setBounds(10, 11, 35, 14);
		panel_Boletim.add(lblRgmBoletim);

		tf_RgmBoletim = new JFormattedTextField(new MaskFormatter("########"));
		tf_RgmBoletim.setBounds(50, 8, 123, 20);
		panel_Boletim.add(tf_RgmBoletim);


		JLabel lblCursoBoletim = new JLabel("Curso");
		lblCursoBoletim.setBounds(10, 39, 46, 14);
		panel_Boletim.add(lblCursoBoletim);

		tf_CursoBoletim = new JTextField();
		tf_CursoBoletim.setBounds(50, 36, 472, 20);
		panel_Boletim.add(tf_CursoBoletim);
		tf_CursoBoletim.setColumns(10);
		tf_CursoBoletim.setEditable(false);

		JLabel lblPooBoletim = new JLabel("Programação Orientada a Objetos");
		lblPooBoletim.setBounds(10, 64, 233, 14);
		panel_Boletim.add(lblPooBoletim);

		JLabel lblNotaPooBoletim = new JLabel("Nota");
		lblNotaPooBoletim.setBounds(10, 89, 46, 14);
		panel_Boletim.add(lblNotaPooBoletim);

		tf_NotaBoletim = new JTextField();
		tf_NotaBoletim.setBounds(50, 89, 46, 20);
		panel_Boletim.add(tf_NotaBoletim);
		tf_NotaBoletim.setColumns(10);
		tf_NotaBoletim.setEditable(false);

		JLabel lblFaltasPooBoletim = new JLabel("Faltas");
		lblFaltasPooBoletim.setBounds(125, 89, 46, 14);
		panel_Boletim.add(lblFaltasPooBoletim);

		tf_FaltasBoletim = new JTextField();
		tf_FaltasBoletim.setBounds(170, 89, 46, 20);
		panel_Boletim.add(tf_FaltasBoletim);
		tf_FaltasBoletim.setColumns(10);
		tf_FaltasBoletim.setEditable(false);

		JLabel lblBancoDadosBoletim = new JLabel("Banco de Dados");
		lblBancoDadosBoletim.setBounds(10, 120, 95, 14);
		panel_Boletim.add(lblBancoDadosBoletim);

		JLabel lblNotaBancoDadosBoletim = new JLabel("Nota");
		lblNotaBancoDadosBoletim.setBounds(10, 145, 46, 14);
		panel_Boletim.add(lblNotaBancoDadosBoletim);

		tf_NotaBancoDadosBoletim = new JTextField();
		tf_NotaBancoDadosBoletim.setEditable(false);
		tf_NotaBancoDadosBoletim.setColumns(10);
		tf_NotaBancoDadosBoletim.setBounds(50, 145, 46, 20);
		panel_Boletim.add(tf_NotaBancoDadosBoletim);

		JLabel lblFaltasBancoDadosBoletim = new JLabel("Faltas");
		lblFaltasBancoDadosBoletim.setBounds(125, 145, 46, 14);
		panel_Boletim.add(lblFaltasBancoDadosBoletim);

		tf_FaltasBancoDadosBoletim = new JTextField();
		tf_FaltasBancoDadosBoletim.setEditable(false);
		tf_FaltasBancoDadosBoletim.setColumns(10);
		tf_FaltasBancoDadosBoletim.setBounds(170, 142, 46, 20);
		panel_Boletim.add(tf_FaltasBancoDadosBoletim);

		JLabel lblProgramacaoWebBoletim = new JLabel("Programação WEB");
		lblProgramacaoWebBoletim.setBounds(10, 170, 116, 14);
		panel_Boletim.add(lblProgramacaoWebBoletim);

		JLabel lblNotaProgramacaoWebBoletim = new JLabel("Nota");
		lblNotaProgramacaoWebBoletim.setBounds(10, 198, 46, 14);
		panel_Boletim.add(lblNotaProgramacaoWebBoletim);

		tf_NotaProgramacaoWebBoletim = new JTextField();
		tf_NotaProgramacaoWebBoletim.setEditable(false);
		tf_NotaProgramacaoWebBoletim.setColumns(10);
		tf_NotaProgramacaoWebBoletim.setBounds(50, 195, 46, 20);
		panel_Boletim.add(tf_NotaProgramacaoWebBoletim);

		JLabel lblFaltasProgramacaoWebBoletim = new JLabel("Faltas");
		lblFaltasProgramacaoWebBoletim.setBounds(125, 198, 46, 14);
		panel_Boletim.add(lblFaltasProgramacaoWebBoletim);

		tf_FaltasProgramacaoWebBoletim = new JTextField();
		tf_FaltasProgramacaoWebBoletim.setEditable(false);
		tf_FaltasProgramacaoWebBoletim.setColumns(10);
		tf_FaltasProgramacaoWebBoletim.setBounds(170, 195, 46, 20);
		panel_Boletim.add(tf_FaltasProgramacaoWebBoletim);

		JButton btnConsultarBoletim = new JButton("");
		btnConsultarBoletim.setIcon(new ImageIcon("C:\\Users\\Pichau\\Documents\\Projeto-Sistema-Academico-JAVA\\sistemaAcademico\\img\\consultar.png"));
		btnConsultarBoletim.setBounds(451, 132, 71, 73);
		panel_Boletim.add(btnConsultarBoletim);
		
		JLabel lblSelecionarSemestre = new JLabel("Selecionar Semestre");
		lblSelecionarSemestre.setBounds(343, 64, 123, 14);
		panel_Boletim.add(lblSelecionarSemestre);
		
		JComboBox bc_Selecionar_Semestre = new JComboBox();
		bc_Selecionar_Semestre.setModel(new DefaultComboBoxModel(new String[] {"1º", "2º", "3º", "4º", "5º", "6º", "7º", "8º"}));
		bc_Selecionar_Semestre.setBounds(476, 62, 46, 22);
		panel_Boletim.add(bc_Selecionar_Semestre);

		// Botões gerais da tela principal, posicionados abaixo das abas/campos
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(458, 39, 89, 23);
		contentPane.add(btnNovo);

		// ===================== INÍCIO: SALVAR ALUNO E CURSO =====================
		// Pega os dados digitados nas abas de aluno/curso e grava no banco
		mntmSalvar_Aluno_Curso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Cria um objeto Aluno para receber os dados digitados na tela
					aluno = new Aluno();

					aluno.setRgm(ftf_Rgm.getText());
					aluno.setNome(ftf_Nome.getText());

					/*
					 A data vem da tela no formato dd/MM/yyyy, então aqui ela é convertida para yyyy-MM-dd, 
					 que é o formato aceito pelo MySQL no campo DATE..
					 * */
					String dataTela = ftf_Dt_Nasc.getText();
					String[] partes = dataTela.split("/");
					String dataBanco = partes[2] + "-" + partes[1] + "-" + partes[0];
					aluno.setData_nasc(dataBanco);

					aluno.setCpf(ftf_Cpf.getText().replace(".", "").replace("-", ""));
					aluno.setEmail(ftf_Email.getText());
					aluno.setEndereco(ftf_Endereco.getText());
					aluno.setMunicipio(ftf_Municipio.getText());
					aluno.setUf((String) cb_Uf.getSelectedItem());
					aluno.setNumero(ftf_Celular.getText());

					// Cria um objeto Curso para guardar as informações da aba Curso
					curso = new Curso();

					curso.setCurso((String) cb_Curso.getSelectedItem());
					curso.setCampus((String) cb_Campus.getSelectedItem());

					// Variável usada para guardar qual radio button de período foi marcado
					String periodoSelecionado = "";

					// Verifica qual período foi escolhido pelo usuário
					if (rdbtn_Matutino.isSelected()) {
						periodoSelecionado = "Matutino";
					} else if (rdbtn_Vespertino.isSelected()) {
						periodoSelecionado = "Vespertino";
					} else if (rdbtn_Noturno.isSelected()) {
						periodoSelecionado = "Noturno";
					}

					// Se nenhum período foi selecionado, o cadastro/alteração é interrompido
					if (periodoSelecionado.equals("")) {
						JOptionPane.showMessageDialog(null, "Selecione um período!");
						return;
					}

					curso.setPeriodo(periodoSelecionado);

					// Junta Aluno e Curso no mesmo objeto para enviar ao DAO
					alunoCursoNota = new AlunoCursoNota(aluno, curso, null);

					// Cria o DAO para acessar o banco de dados
					dao = new LeitorDAO();
					// Envia os dados de aluno e curso para serem salvos no banco
					dao.salvarAlunoCurso(alunoCursoNota);

					JOptionPane.showMessageDialog(null, "Aluno e curso salvos com sucesso!");

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});



		// ===================== INÍCIO: ALTERAR ALUNO E CURSO =====================
		// Atualiza os dados do aluno e do curso com base no RGM digitado
		mntmAlterar_Aluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					aluno = new Aluno();

					aluno.setRgm(ftf_Rgm.getText());
					aluno.setNome(ftf_Nome.getText());

					String dataTela = ftf_Dt_Nasc.getText();
					String[] partes = dataTela.split("/");
					String dataBanco = partes[2] + "-" + partes[1] + "-" + partes[0];
					aluno.setData_nasc(dataBanco);

					aluno.setCpf(ftf_Cpf.getText().replace(".", "").replace("-", ""));
					aluno.setEmail(ftf_Email.getText());
					aluno.setEndereco(ftf_Endereco.getText());
					aluno.setMunicipio(ftf_Municipio.getText());
					aluno.setUf((String) cb_Uf.getSelectedItem());
					aluno.setNumero(ftf_Celular.getText());

					curso = new Curso();

					curso.setCurso((String) cb_Curso.getSelectedItem());
					curso.setCampus((String) cb_Campus.getSelectedItem());

					String periodoSelecionado = "";

					if (rdbtn_Matutino.isSelected()) {
						periodoSelecionado = "Matutino";
					} else if (rdbtn_Vespertino.isSelected()) {
						periodoSelecionado = "Vespertino";
					} else if (rdbtn_Noturno.isSelected()) {
						periodoSelecionado = "Noturno";
					}

					if (periodoSelecionado.equals("")) {
						JOptionPane.showMessageDialog(null, "Selecione um período!");
						return;
					}

					curso.setPeriodo(periodoSelecionado);

					alunoCursoNota = new AlunoCursoNota(aluno, curso, null);

					dao = new LeitorDAO();
					// Envia os dados atualizados para o banco
					dao.alterarAlunoCurso(alunoCursoNota);

					JOptionPane.showMessageDialog(null, "Aluno e curso alterados com sucesso!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});


		// ===================== INÍCIO: CONSULTAR ALUNO E CURSO =====================
		// Pesquisa pelo RGM e joga os dados encontrados de volta nos campos da tela
		mntmConsultar_Aluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rgm = ftf_Rgm.getText();

					dao = new LeitorDAO();
					// Consulta o aluno e o curso usando o RGM informado
					alunoCursoNota = dao.consultarAlunoCurso(rgm);

					// Se não encontrou nenhum aluno com esse RGM, limpa/para a ação
					if (alunoCursoNota == null) {
						JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
						return;
					}

					// Se encontrou, separa os objetos para preencher os campos da tela
					aluno = alunoCursoNota.getAluno();
					curso = alunoCursoNota.getCurso();

					ftf_Rgm.setText(aluno.getRgm());
					ftf_Nome.setText(aluno.getNome());

					// A data vem do banco como yyyy-MM-dd e será convertida para aparecer como dd/MM/yyyy
					String dataBanco = aluno.getData_nasc();

					// Confere se a data veio no formato esperado antes de separar
					if (dataBanco != null && dataBanco.contains("-")) {
						String[] partes = dataBanco.split("-");
						String dataTela = partes[2] + "/" + partes[1] + "/" + partes[0];
						ftf_Dt_Nasc.setText(dataTela);
					} else {
						ftf_Dt_Nasc.setText(dataBanco);
					}

					ftf_Cpf.setText(aluno.getCpf());
					ftf_Email.setText(aluno.getEmail());
					ftf_Endereco.setText(aluno.getEndereco());
					ftf_Municipio.setText(aluno.getMunicipio());
					cb_Uf.setSelectedItem(aluno.getUf());
					ftf_Celular.setText(aluno.getNumero());

					cb_Curso.setSelectedItem(curso.getCurso());
					cb_Campus.setSelectedItem(curso.getCampus());

					// Limpa a seleção anterior antes de marcar o período vindo do banco
					periodo.clearSelection();

					// Marca na tela o período que veio cadastrado no banco
					if (curso.getPeriodo().equals("Matutino")) {
						rdbtn_Matutino.setSelected(true);
					} else if (curso.getPeriodo().equals("Vespertino")) {
						rdbtn_Vespertino.setSelected(true);
					} else if (curso.getPeriodo().equals("Noturno")) {
						rdbtn_Noturno.setSelected(true);
					}

					JOptionPane.showMessageDialog(null, "Aluno encontrado!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});



		// ===================== INÍCIO: EXCLUIR ALUNO =====================
		// Exclui o aluno, o curso e as notas ligadas ao RGM informado
		mntmExcluir_Aluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rgm = ftf_Rgm.getText();

					// Antes de excluir, pede confirmação para evitar apagar por acidente
					int resposta = JOptionPane.showConfirmDialog(
							null,
							"Deseja excluir este aluno, curso e todas as notas dele?",
							"Confirmar exclusão",
							JOptionPane.YES_NO_OPTION
					);

					// Se o usuário escolher "Não", a exclusão é cancelada
					if (resposta != JOptionPane.YES_OPTION) {
						return;
					}

					dao = new LeitorDAO();
					// Exclui do banco as notas, o curso e o aluno ligados a esse RGM
					dao.excluirAluno(rgm);

					ftf_Rgm.setText(null);
					ftf_Nome.setText(null);
					ftf_Dt_Nasc.setText(null);
					ftf_Cpf.setText(null);
					ftf_Email.setText(null);
					ftf_Endereco.setText(null);
					ftf_Municipio.setText(null);
					ftf_Celular.setText(null);
					cb_Uf.setSelectedIndex(0);
					cb_Curso.setSelectedIndex(0);
					cb_Campus.setSelectedIndex(0);
					periodo.clearSelection();

					JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});


		// ===================== INÍCIO: SALVAR NOTA =====================
		// Salva uma nota/falta para o aluno, disciplina e semestre selecionados
		mntmSalvar_Nf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Cria um objeto Nota para guardar disciplina, semestre, nota e faltas
					nota = new Nota();
					
					nota.setRgm(ftf_Rgm_pesquisa.getText());
					nota.setDisciplina((String) cb_Disciplina.getSelectedItem());
					nota.setSemestre((String) cb_Semestre.getSelectedItem());
					nota.setNota((String) cb_Nota.getSelectedItem());
					nota.setFaltas(Integer.parseInt(ftf_Faltas.getText()));
					
					dao = new LeitorDAO();
					// Salva a nota no banco
					dao.salvarNota(nota);
					
					JOptionPane.showMessageDialog(null, "Nota salva com sucesso!");
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});



		// ===================== INÍCIO: ALTERAR NOTA =====================
		// Altera a nota e as faltas usando RGM + disciplina + semestre como referência
		mntmAlterar_Nf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nota = new Nota();

					nota.setRgm(ftf_Rgm_pesquisa.getText());
					nota.setDisciplina((String) cb_Disciplina.getSelectedItem());
					nota.setSemestre((String) cb_Semestre.getSelectedItem());
					nota.setNota((String) cb_Nota.getSelectedItem());
					nota.setFaltas(Integer.parseInt(ftf_Faltas.getText()));

					dao = new LeitorDAO();
					// Altera a nota já cadastrada no banco
					dao.alterarNota(nota);

					JOptionPane.showMessageDialog(null, "Nota alterada com sucesso!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});



		// ===================== INÍCIO: CONSULTAR NOTA =====================
		// Busca o aluno e a nota da disciplina/semestre selecionados
		mntmConsultar_Nf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rgm = ftf_Rgm_pesquisa.getText();
					String disciplina = (String) cb_Disciplina.getSelectedItem();
					String semestre = (String) cb_Semestre.getSelectedItem();

					dao = new LeitorDAO();

					// Primeiro consulta Aluno + Curso para preencher os campos de resultado desta aba
					alunoCursoNota = dao.consultarAlunoCurso(rgm);

					if (alunoCursoNota == null) {
						JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
						tf_Resultado_Busca_Nome.setText(null);
						tf_Resultado_Busca_Curso.setText(null);
						cb_Nota.setSelectedIndex(0);
						ftf_Faltas.setText(null);
						return;
					}

					aluno = alunoCursoNota.getAluno();
					curso = alunoCursoNota.getCurso();

					tf_Resultado_Busca_Nome.setText(aluno.getNome());
					tf_Resultado_Busca_Curso.setText(curso.getCurso());

					// Depois consulta a nota da disciplina/semestre selecionados
					// Consulta a nota usando a combinação RGM + disciplina + semestre
					nota = dao.consultarNota(rgm, disciplina, semestre);

					// Se não houver nota cadastrada para essa combinação, avisa e limpa os campos de nota
					if (nota == null) {
						JOptionPane.showMessageDialog(null, "Aluno encontrado, mas não existe nota cadastrada para essa disciplina e semestre.");
						cb_Nota.setSelectedIndex(0);
						ftf_Faltas.setText(null);
						return;
					}

					cb_Nota.setSelectedItem(nota.getNota());
					ftf_Faltas.setText(String.valueOf(nota.getFaltas()));

					JOptionPane.showMessageDialog(null, "Dados de notas e faltas encontrados!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});


		// ===================== INÍCIO: EXCLUIR NOTA =====================
		// Exclui somente a nota da disciplina e semestre selecionados
		mntmExcluir_Nf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rgm = ftf_Rgm_pesquisa.getText();
					String disciplina = (String) cb_Disciplina.getSelectedItem();
					String semestre = (String) cb_Semestre.getSelectedItem();

					int resposta = JOptionPane.showConfirmDialog(
							null,
							"Deseja excluir a nota desta disciplina e semestre?",
							"Confirmar exclusão",
							JOptionPane.YES_NO_OPTION
					);

					if (resposta != JOptionPane.YES_OPTION) {
						return;
					}

					dao = new LeitorDAO();
					// Exclui somente a nota selecionada, sem apagar aluno ou curso
					dao.excluirNota(rgm, disciplina, semestre);

					cb_Nota.setSelectedIndex(0);
					ftf_Faltas.setText(null);

					JOptionPane.showMessageDialog(null, "Nota excluída com sucesso!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});



		// ===================== INÍCIO: CONSULTAR BOLETIM =====================
		// Busca os dados do aluno e todas as notas do semestre selecionado
		btnConsultarBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rgm = tf_RgmBoletim.getText();
					// No boletim, além do RGM, o semestre também define quais notas serão buscadas
					String semestre = (String) bc_Selecionar_Semestre.getSelectedItem();

					dao = new LeitorDAO();

					// Consulta os dados do aluno e do curso pelo RGM
					alunoCursoNota = dao.consultarAlunoCurso(rgm);

					if (alunoCursoNota == null) {
						JOptionPane.showMessageDialog(null, "Aluno não encontrado!");
						tf_NomeBoletim.setText(null);
						tf_CursoBoletim.setText(null);
						tf_NotaBoletim.setText(null);
						tf_FaltasBoletim.setText(null);
						tf_NotaBancoDadosBoletim.setText(null);
						tf_FaltasBancoDadosBoletim.setText(null);
						tf_NotaProgramacaoWebBoletim.setText(null);
						tf_FaltasProgramacaoWebBoletim.setText(null);
						return;
					}

					aluno = alunoCursoNota.getAluno();
					curso = alunoCursoNota.getCurso();

					tf_RgmBoletim.setText(aluno.getRgm());
					tf_NomeBoletim.setText(aluno.getNome());
					tf_CursoBoletim.setText(curso.getCurso());

					// Limpa os campos antes de preencher novamente
					tf_NotaBoletim.setText(null);
					tf_FaltasBoletim.setText(null);
					tf_NotaBancoDadosBoletim.setText(null);
					tf_FaltasBancoDadosBoletim.setText(null);
					tf_NotaProgramacaoWebBoletim.setText(null);
					tf_FaltasProgramacaoWebBoletim.setText(null);

					// Consulta todas as notas do aluno naquele semestre
					// Busca todas as notas daquele aluno no semestre escolhido
					List<Nota> notas = dao.consultarNotasPorSemestre(rgm, semestre);

					// Se o aluno existe, mas não tem notas nesse semestre, o boletim não é preenchido
					if (notas.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Aluno encontrado, mas não há notas cadastradas para esse semestre.");
						return;
					}

					// Percorre as notas encontradas e coloca cada uma no campo correto do boletim
					for (Nota n : notas) {
						String disciplina = n.getDisciplina();

						// Cada disciplina tem seu próprio campo de nota e faltas no boletim
						if (disciplina.equals("Programação Orientada a Objeto")) {
							tf_NotaBoletim.setText(n.getNota());
							tf_FaltasBoletim.setText(String.valueOf(n.getFaltas()));
						} else if (disciplina.equals("Banco de dados")) {
							tf_NotaBancoDadosBoletim.setText(n.getNota());
							tf_FaltasBancoDadosBoletim.setText(String.valueOf(n.getFaltas()));
						} else if (disciplina.equals("Programação WEB")) {
							tf_NotaProgramacaoWebBoletim.setText(n.getNota());
							tf_FaltasProgramacaoWebBoletim.setText(String.valueOf(n.getFaltas()));
						}
					}

					JOptionPane.showMessageDialog(null, "Boletim consultado com sucesso!");

				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "ERRO: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});

		mntmSair_Aluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mntmSobre_Ajuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "informações do Menu");
			}
		});

		// ===================== INÍCIO: BOTÃO NOVO =====================
		// Limpa os campos da tela para começar outro cadastro ou consulta
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftf_Rgm.setText(null);
				ftf_Nome.setText(null);
				ftf_Dt_Nasc.setText(null);
				ftf_Cpf.setText(null);
				ftf_Email.setText(null);
				ftf_Endereco.setText(null);
				ftf_Municipio.setText(null);
				ftf_Celular.setText(null);
				ftf_Rgm_pesquisa.setText(null);
				tf_Resultado_Busca_Nome.setText(null);
				tf_Resultado_Busca_Curso.setText(null);
				ftf_Faltas.setText(null);
				tf_RgmBoletim.setText(null);
				tf_NomeBoletim.setText(null);
				tf_CursoBoletim.setText(null);
				tf_NotaBoletim.setText(null);
				tf_FaltasBoletim.setText(null);
				tf_NotaBancoDadosBoletim.setText(null);
				tf_FaltasBancoDadosBoletim.setText(null);
				tf_NotaProgramacaoWebBoletim.setText(null);
				tf_FaltasProgramacaoWebBoletim.setText(null);
				cb_Uf.setSelectedIndex(0);
				cb_Curso.setSelectedIndex(0);
				cb_Campus.setSelectedIndex(0);
				cb_Disciplina.setSelectedIndex(0);
				cb_Semestre.setSelectedIndex(0);
				cb_Nota.setSelectedIndex(0);
				bc_Selecionar_Semestre.setSelectedIndex(0);
				periodo.clearSelection();
			}
		});
	}
}