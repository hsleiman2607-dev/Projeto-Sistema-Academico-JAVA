package sistemaAcademico;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_Resultado_Busca_Nome;
	private JTextField tf_Resultado_Busca_Curso;

	
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

// INICIO DO FRAME
// throws Exception serve para garantir que nao tenha erros no MaskFormatter
	public Gui() throws Exception {
		
		setTitle("Sistema Acadêmico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Aumentei um pouco a janela para acomodar melhor os elementos
        setBounds(100, 100, 579, 355); 
// Impede que o usuário desconfigure o layout arrastando a tela 
        setResizable(false);        
//Margem que guarda o menu superior
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAluno = new JMenu("Aluno");
		menuBar.add(mnAluno);
		
		JMenuItem mntmSalvar_Aluno = new JMenuItem("Salvar");
		mntmSalvar_Aluno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnAluno.add(mntmSalvar_Aluno);
		
		JMenuItem mntmAlterar_Aluno = new JMenuItem("Alterar");
		mnAluno.add(mntmAlterar_Aluno);
		
		JMenuItem mntmConsultar_Aluno = new JMenuItem("Consultar");
		mnAluno.add(mntmConsultar_Aluno);
		
		JMenuItem mntmExcluir_Aluno = new JMenuItem("Excluir");
		mnAluno.add(mntmExcluir_Aluno);
		
		JSeparator separator = new JSeparator();
		mnAluno.add(separator);
		
// Botao para sair que esta dentro do menu superior Aluno
		JMenuItem mntmSair_Aluno = new JMenuItem("Sair");
		mntmSair_Aluno.addActionListener(new ActionListener() {
// Metodo para fechar o aplicativo
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
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
		
// Menu Ajuda
		JMenu mnAjuda = new JMenu("Ajuda");
        menuBar.add(mnAjuda);

// ITEM TUTORIAL ADICIONADO AQUI
        JMenuItem mntmTutorial = new JMenuItem("Tutorial");
        mntmTutorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textoTutorial = "COMO USAR O SISTEMA:\n\n" +
                        "1. DADOS PESSOAIS: Preencha RGM, Nome e documentos do aluno.\n" +
                        "2. CURSO: Escolha a formação e o turno. Use os botões CRUD para salvar.\n" +
                        "3. NOTAS E FALTAS: Digite o RGM para buscar e lançar notas por disciplina.\n" +
                        "4. BOLETIM: Digite o RGM e clique em 'Gerar' para ver o histórico.\n\n";
                JOptionPane.showMessageDialog(null, textoTutorial, "Tutorial de Uso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        mnAjuda.add(mntmTutorial);

        JMenuItem mntmSobre_Ajuda = new JMenuItem("Sobre");
        mntmSobre_Ajuda.addActionListener(e -> 
            JOptionPane.showMessageDialog(null, "Sistema Acadêmico v1.0\nDesenvolvido para gestão escolar.")
        );
        mnAjuda.add(mntmSobre_Ajuda);

// ====================== INICIO CONTENTPANE ==========================
// Area de delimitacao do conteudo principal da janela
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
// ======================= INICIO TABBEDPANE ==========================
// Area de delimitacao do menu interno do sistema
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 537, 244);
// ======================= FIM TABBEDPANE ==========================
		contentPane.add(tabbedPane);
		
// ==================== INICIO MENU DADOS PESSOAIS ==========================
/*
lbl == LABEL
ftf == FormattedTextField
cb == COMBO BOX

==================== */
		
		JPanel panel_Dados_Pessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, panel_Dados_Pessoais, null);
		panel_Dados_Pessoais.setLayout(null);
		
		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRgm.setBounds(10, 11, 32, 14);
		panel_Dados_Pessoais.add(lblRgm);
		
		JFormattedTextField ftf_Rgm = new JFormattedTextField();
		ftf_Rgm.setBounds(44, 9, 118, 20);
		panel_Dados_Pessoais.add(ftf_Rgm);
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(172, 11, 35, 14);
		panel_Dados_Pessoais.add(lblNome);
		
		JFormattedTextField ftf_Nome = new JFormattedTextField();
		ftf_Nome.setBounds(212, 8, 310, 20);
		panel_Dados_Pessoais.add(ftf_Nome);
		
		JLabel lblDt_Nascmento = new JLabel("Data de Nascimento");
		lblDt_Nascmento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDt_Nascmento.setBounds(10, 52, 110, 14);
		panel_Dados_Pessoais.add(lblDt_Nascmento);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(257, 52, 26, 14);
		panel_Dados_Pessoais.add(lblCpf);
		
		JFormattedTextField ftf_Cpf = new JFormattedTextField(new MaskFormatter("###.###.###-#"));
		ftf_Cpf.setHorizontalAlignment(SwingConstants.LEFT);
		ftf_Cpf.setBounds(285, 49, 237, 20);
		panel_Dados_Pessoais.add(ftf_Cpf);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(10, 98, 32, 14);
		panel_Dados_Pessoais.add(lblEmail);
		
		JFormattedTextField ftf_Email = new JFormattedTextField();
		ftf_Email.setBounds(48, 95, 474, 20);
		panel_Dados_Pessoais.add(ftf_Email);
		
		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndereco.setBounds(10, 135, 54, 14);
		panel_Dados_Pessoais.add(lblEndereco);
		
		JFormattedTextField ftf_Endereco = new JFormattedTextField();
		ftf_Endereco.setBounds(69, 132, 453, 20);
		panel_Dados_Pessoais.add(ftf_Endereco);
		
		JLabel lblMunicipio = new JLabel("Município");
		lblMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMunicipio.setBounds(10, 171, 46, 14);
		panel_Dados_Pessoais.add(lblMunicipio);
		
		JFormattedTextField ftf_Municipio = new JFormattedTextField();
		ftf_Municipio.setBounds(65, 168, 127, 20);
		panel_Dados_Pessoais.add(ftf_Municipio);
		
		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUf.setBounds(202, 171, 17, 14);
		panel_Dados_Pessoais.add(lblUf);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCelular.setBounds(278, 171, 46, 14);
		panel_Dados_Pessoais.add(lblCelular);
		
		JFormattedTextField ftf_Celular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		ftf_Celular.setBounds(320, 168, 202, 20);
		panel_Dados_Pessoais.add(ftf_Celular);
		
		JComboBox<String> cb_Uf = new JComboBox<>();
        cb_Uf.setModel(new DefaultComboBoxModel<>(new String[] 
        		{"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
        				"MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
        cb_Uf.setBounds(222, 168, 46, 22);
        panel_Dados_Pessoais.add(cb_Uf);
        
// new MaskFormatter serve para formatar o campo
		JFormattedTextField ftf_Dt_Nasc = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftf_Dt_Nasc.setHorizontalAlignment(SwingConstants.CENTER);
		ftf_Dt_Nasc.setBounds(126, 51, 110, 20);
		panel_Dados_Pessoais.add(ftf_Dt_Nasc);
		
//==================== FIM MENU DADOS PESSOAIS ==========================
		
//==================== INICIO MENU CURSO ==========================
/*
lbl == LABEL
cb == COMBO BOX
rdbtn == RADIO BUTTON
btn == BUTTOM
 
*==================== */
		JPanel panel_Curso = new JPanel();
		tabbedPane.addTab("Curso", null, panel_Curso, null);
		panel_Curso.setLayout(null);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(10, 15, 46, 14);
		panel_Curso.add(lblCurso);
		
		JComboBox<String> cb_Curso = new JComboBox<>();
        cb_Curso.setModel(new DefaultComboBoxModel<>(new String[] {"Análise e Desenvolvimento de Sistemas", "Ciência da Computação"}));
        cb_Curso.setBounds(63, 11, 459, 22);
        panel_Curso.add(cb_Curso);;
		
		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setBounds(10, 55, 46, 14);
		panel_Curso.add(lblCampus);
		
		JComboBox<String> cb_Campus = new JComboBox<>();
        cb_Campus.setModel(new DefaultComboBoxModel<>(new String[] {"Tatuapé", "Pinheiros/Villa-Lobos", "EAD"}));
        cb_Campus.setBounds(63, 51, 459, 22);
        panel_Curso.add(cb_Campus);
		
		JLabel lblNewLabel = new JLabel("Período");
		lblNewLabel.setBounds(10, 88, 46, 14);
		panel_Curso.add(lblNewLabel);
		
		
		JRadioButton rdbtn_Matutino = new JRadioButton("Matutino");
		rdbtn_Matutino.setBounds(65, 84, 93, 23);
		panel_Curso.add(rdbtn_Matutino);
		
		JRadioButton rdbtn_Vespertino = new JRadioButton("Vespertino");
		rdbtn_Vespertino.setBounds(160, 84, 109, 23);
		panel_Curso.add(rdbtn_Vespertino);
		
		JRadioButton rdbtn_Noturno = new JRadioButton("Noturno");
		rdbtn_Noturno.setBounds(267, 84, 109, 23);
		panel_Curso.add(rdbtn_Noturno);
		
// Criação do objeto PERIODO, que tem a função de fazer com que todos os RadioButtons façam parte do mesmo grupo.
		ButtonGroup periodo = new ButtonGroup();
// .ADD adiciona todos no mesmo grupo
		periodo.add(rdbtn_Matutino);
		periodo.add(rdbtn_Vespertino);
		periodo.add(rdbtn_Noturno);
		
//---------------------------- BOTOES QUE NAO SABEMOS OQ FAZER ----------------------------------
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 126, 71, 66);
		panel_Curso.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(116, 126, 71, 66);
		panel_Curso.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(234, 126, 71, 66);
		panel_Curso.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(344, 126, 71, 66);
		panel_Curso.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(451, 126, 71, 66);
		panel_Curso.add(btnNewButton_4);
		
		
// ==================== FIM MENU CURSO ==========================
		
// ==================== INICIO MENU NOTAS E FALTAS ==========================
/*
lbl == LABEL
cb == COMBO BOX
rdbtn == RADIO BUTTON
tf == TEXT FIELD
btn == BUTTOM
 
==================== */
		JPanel panel_Notas_Faltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, panel_Notas_Faltas, null);
		panel_Notas_Faltas.setLayout(null);
		
		 JLabel lblRGM_NF = new JLabel("RGM");
	        lblRGM_NF.setBounds(10, 15, 46, 14);
	        panel_Notas_Faltas.add(lblRGM_NF);

	        JFormattedTextField ftf_Rgm_pesquisa = new JFormattedTextField();
	        ftf_Rgm_pesquisa.setBounds(38, 13, 131, 19);
	        panel_Notas_Faltas.add(ftf_Rgm_pesquisa);

	        tf_Resultado_Busca_Nome = new JTextField("Nome do Aluno aparecerá aqui...");
	        tf_Resultado_Busca_Nome.setBounds(179, 12, 343, 20);
	        tf_Resultado_Busca_Nome.setEditable(false);
	        panel_Notas_Faltas.add(tf_Resultado_Busca_Nome);

	        tf_Resultado_Busca_Curso = new JTextField("Curso aparecerá aqui...");
	        tf_Resultado_Busca_Curso.setBounds(10, 45, 512, 20);
	        tf_Resultado_Busca_Curso.setEditable(false);
	        panel_Notas_Faltas.add(tf_Resultado_Busca_Curso);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 79, 64, 14);
		panel_Notas_Faltas.add(lblDisciplina);
		
		JComboBox<String> cb_Disciplina = new JComboBox<>();
        cb_Disciplina.setModel(new DefaultComboBoxModel<>(new String[] 
        		{"Programação Orientada a Objeto", "Banco de dados", "Programação WEB"}));
        cb_Disciplina.setBounds(58, 75, 464, 22);
        panel_Notas_Faltas.add(cb_Disciplina);
        
		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(10, 109, 64, 14);
		panel_Notas_Faltas.add(lblSemestre);
		
		JComboBox<String> cb_Semestre = new JComboBox<>();
        cb_Semestre.setModel(new DefaultComboBoxModel<>(new String[] {"2025-1", "2025-2", "2026-1", "2026-2"}));
        cb_Semestre.setBounds(68, 107, 64, 22);
        panel_Notas_Faltas.add(cb_Semestre);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(142, 109, 46, 14);
		panel_Notas_Faltas.add(lblNota);
		
		JComboBox<String> cb_Nota = new JComboBox<>();
        cb_Nota.setModel(new DefaultComboBoxModel<>(new String[] 
        		{"0", "0,5", "1", "1,5", "2", "2,5", "3", "3,5", "4", "4,5", "5", "5,5",
        				"6", "6,5", "7", "7,5", "8", "8,5", "9", "9,5", "10"}));
        cb_Nota.setBounds(170, 107, 55, 22);
        panel_Notas_Faltas.add(cb_Nota);
		
		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(237, 109, 46, 14);
		panel_Notas_Faltas.add(lblFaltas);
		
		JFormattedTextField ftf_Faltas = new JFormattedTextField();
		ftf_Faltas.setBounds(277, 109, 81, 20);
		panel_Notas_Faltas.add(ftf_Faltas);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(10, 151, 71, 56);
		panel_Notas_Faltas.add(btnNewButton_5);
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setBounds(121, 151, 71, 56);
		panel_Notas_Faltas.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("New button");
		btnNewButton_2_1.setBounds(233, 151, 71, 56);
		panel_Notas_Faltas.add(btnNewButton_2_1);
		
		JButton btnNewButton_3_1 = new JButton("New button");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton_3_1.setBounds(343, 151, 71, 56);
		panel_Notas_Faltas.add(btnNewButton_3_1);
		
		JButton btnNewButton_4_1 = new JButton("New button");
		btnNewButton_4_1.setBounds(451, 151, 71, 56);
		panel_Notas_Faltas.add(btnNewButton_4_1);
		
// ==================== FIM MENU NOTAS E FALTAS ==========================
		
// ==================== INICIO MENU BOLETIM ==========================
		JPanel panel_Boletim = new JPanel();
        tabbedPane.addTab("Boletim", null, panel_Boletim, null);
        panel_Boletim.setLayout(null);

        JLabel lblRgmBol = new JLabel("RGM do Aluno:");
        lblRgmBol.setBounds(10, 20, 100, 14);
        panel_Boletim.add(lblRgmBol);

        JTextField txtRgmBol = new JTextField();
        txtRgmBol.setBounds(85, 17, 159, 20);
        panel_Boletim.add(txtRgmBol);

        JButton btnGerarBoletim = new JButton("Gerar Boletim");
        btnGerarBoletim.setBounds(312, 16, 130, 23);
        panel_Boletim.add(btnGerarBoletim);

        JTextArea txtAreaBoletim = new JTextArea();
        txtAreaBoletim.setEditable(false);
        txtAreaBoletim.setText("Aguardando busca...\n");
        
        JScrollPane scrollBoletim = new JScrollPane(txtAreaBoletim);
        scrollBoletim.setBounds(10, 50, 520, 210);
        panel_Boletim.add(scrollBoletim);

// Ação fictícia para gerar boletim
        btnGerarBoletim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaBoletim.setText("----------------------------------------------------\n");
                txtAreaBoletim.append(" BOLETIM DO ALUNO - RGM: " + txtRgmBol.getText() + "\n");
                txtAreaBoletim.append("----------------------------------------------------\n");
                txtAreaBoletim.append("Disciplina: Programação Orientada a Objetos\n");
                txtAreaBoletim.append("Nota: 8.5\n");
                txtAreaBoletim.append("Faltas: 2\n");
                txtAreaBoletim.append("Situação: Aprovado\n");
            }
        });
        
// ==================== FIM ABA BOLETIM ==========================
        
    } 
}


