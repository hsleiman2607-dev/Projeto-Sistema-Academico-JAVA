package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Curso;
import model.Nota;
import model.AlunoCursoNota;
import util.ConnectionFactory;

public class LeitorDAO {

	// Objetos principais para conversar com o banco
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	// Quando o DAO é criado, a conexão com o banco já é aberta
	public LeitorDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("ERRO AO CONECTAR: " + e.getMessage());
		}
	}

	// ===================== INÍCIO: SALVAR ALUNO E CURSO =====================
	// Salva primeiro o aluno e depois o curso, porque o curso depende do RGM do aluno.
	public void salvarAlunoCurso(AlunoCursoNota alunoCursoNota) throws Exception {
		try {
			// Separa o objeto "caixinha" em aluno e curso para salvar em tabelas diferentes
			Aluno aluno = alunoCursoNota.getAluno();
			Curso curso = alunoCursoNota.getCurso();

			String sqlAluno = "INSERT INTO tb_aluno(pk_rgm, pk_cpf, nome, data_nasc, email, numero, endereco, municipio, uf) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(sqlAluno);

			// Preenche os ? do INSERT do aluno na mesma ordem das colunas
			ps.setString(1, aluno.getRgm());
			ps.setString(2, aluno.getCpf());
			ps.setString(3, aluno.getNome());
			ps.setString(4, aluno.getData_nasc());
			ps.setString(5, aluno.getEmail());
			ps.setString(6, aluno.getNumero());
			ps.setString(7, aluno.getEndereco());
			ps.setString(8, aluno.getMunicipio());
			ps.setString(9, aluno.getUf());

			ps.executeUpdate();

			String sqlCurso = "INSERT INTO tb_curso(curso, campus, periodo, fk_rgm) "
					+ "VALUES(?, ?, ?, ?)";

			// Prepara o comando do curso depois que o aluno já foi salvo
			ps = conn.prepareStatement(sqlCurso);

			ps.setString(1, curso.getCurso());
			ps.setString(2, curso.getCampus());
			ps.setString(3, curso.getPeriodo());

			// Aqui o RGM do aluno vira chave estrangeira na tabela de curso
			ps.setString(4, aluno.getRgm());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception("ERRO AO SALVAR ALUNO E CURSO: " + e.getMessage());
		}
	}
	// ===================== FIM: SALVAR ALUNO E CURSO =====================

	// ===================== INÍCIO: ALTERAR ALUNO E CURSO =====================
	// Atualiza os dados usando o RGM como referência. O RGM identifica qual registro será alterado.
	public void alterarAlunoCurso(AlunoCursoNota alunoCursoNota) throws Exception {
		try {
			Aluno aluno = alunoCursoNota.getAluno();
			Curso curso = alunoCursoNota.getCurso();

			String sqlAluno = "UPDATE tb_aluno SET pk_cpf = ?, nome = ?, data_nasc = ?, email = ?, numero = ?, endereco = ?, municipio = ?, uf = ? "
					+ "WHERE pk_rgm = ?";

			ps = conn.prepareStatement(sqlAluno);

			ps.setString(1, aluno.getCpf());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getData_nasc());
			ps.setString(4, aluno.getEmail());
			ps.setString(5, aluno.getNumero());
			ps.setString(6, aluno.getEndereco());
			ps.setString(7, aluno.getMunicipio());
			ps.setString(8, aluno.getUf());

			// O último parâmetro é o RGM usado no WHERE
			ps.setString(9, aluno.getRgm());

			ps.executeUpdate();

			String sqlCurso = "UPDATE tb_curso SET curso = ?, campus = ?, periodo = ? "
					+ "WHERE fk_rgm = ?";

			ps = conn.prepareStatement(sqlCurso);

			ps.setString(1, curso.getCurso());
			ps.setString(2, curso.getCampus());
			ps.setString(3, curso.getPeriodo());
			ps.setString(4, aluno.getRgm());

			ps.executeUpdate();

		} catch(Exception e) {
			throw new Exception("ERRO AO ALTERAR ALUNO E CURSO: " + e.getMessage());
		}
	}
	// ===================== FIM: ALTERAR ALUNO E CURSO =====================

	// ===================== INÍCIO: SALVAR NOTA =====================
	// Cadastra uma nota para uma disciplina de um aluno em determinado semestre.
	public void salvarNota(Nota nota) throws Exception {
		try {
			String sqlNota = "INSERT INTO tb_notas(disciplina, semestre, nota, faltas, fk_rgm) "
					+ "VALUES(?, ?, ?, ?, ?)";

			// Prepara o comando SQL da tabela de notas
			ps = conn.prepareStatement(sqlNota);

			ps.setString(1, nota.getDisciplina());
			ps.setString(2, nota.getSemestre());
			ps.setString(3, nota.getNota());
			ps.setInt(4, nota.getFaltas());

			// Liga a nota ao aluno pelo RGM
			ps.setString(5, nota.getRgm());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception("ERRO AO SALVAR NOTA: " + e.getMessage());
		}
	}
	// ===================== FIM: SALVAR NOTA =====================

	// ===================== INÍCIO: ALTERAR NOTA =====================
	// Altera apenas nota e faltas. A disciplina e o semestre servem para localizar o registro certo.
	public void alterarNota(Nota nota) throws Exception {
		try {
			String sqlNota = "UPDATE tb_notas SET nota = ?, faltas = ? "
					+ "WHERE fk_rgm = ? AND disciplina = ? AND semestre = ?"; // garante que só uma nota específica seja afetada

			ps = conn.prepareStatement(sqlNota);

			ps.setString(1, nota.getNota());
			ps.setInt(2, nota.getFaltas());

			// Esses três campos identificam exatamente qual nota deve ser alterada
			ps.setString(3, nota.getRgm());
			ps.setString(4, nota.getDisciplina());
			ps.setString(5, nota.getSemestre());

			ps.executeUpdate();

		} catch(Exception e) {
			throw new Exception("ERRO AO ALTERAR NOTA: " + e.getMessage());
		}
	}
	// ===================== FIM: ALTERAR NOTA =====================

	// ===================== INÍCIO: CONSULTAR ALUNO E CURSO =====================
	// Busca os dados do aluno junto com o curso. O JOIN junta as duas tabelas pelo RGM.
	public AlunoCursoNota consultarAlunoCurso(String rgm) throws Exception {
		try {
			String sql = "SELECT "
					+ "a.pk_rgm, a.pk_cpf, a.nome, a.data_nasc, a.email, a.numero, a.endereco, a.municipio, a.uf, "
					+ "c.curso, c.campus, c.periodo "
					+ "FROM tb_aluno a "
					+ "INNER JOIN tb_curso c ON a.pk_rgm = c.fk_rgm "
					+ "WHERE a.pk_rgm = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, rgm);

			rs = ps.executeQuery();

			// Se rs.next() for verdadeiro, significa que encontrou um aluno com esse RGM
			// rs.next() tenta ir para o primeiro resultado encontrado no SELECT
			if (rs.next()) {
				// Monta o objeto Aluno com os dados que vieram do banco
				Aluno aluno = new Aluno(
						rs.getString("pk_rgm"),
						rs.getString("pk_cpf"),
						rs.getString("nome"),
						rs.getString("data_nasc"),
						rs.getString("email"),
						rs.getString("numero"),
						rs.getString("endereco"),
						rs.getString("municipio"),
						rs.getString("uf")
				);

				// Monta o objeto Curso com os dados da tabela tb_curso
				Curso curso = new Curso(
						rs.getString("curso"),
						rs.getString("campus"),
						rs.getString("periodo")
				);

				// Retorna os dois objetos juntos para a tela conseguir preencher os campos
				return new AlunoCursoNota(aluno, curso, null);
			}

			// Se não encontrou nada, retorna null para a tela tratar como "não encontrado"
			return null;

		} catch (Exception e) {
			throw new Exception("ERRO AO CONSULTAR ALUNO E CURSO: " + e.getMessage());
		}
	}
	// ===================== FIM: CONSULTAR ALUNO E CURSO =====================

	// ===================== INÍCIO: CONSULTAR NOTA =====================
	// Consulta uma nota específica. Aqui não basta só o RGM, porque o aluno pode ter várias disciplinas.
	public Nota consultarNota(String rgm, String disciplina, String semestre) throws Exception {
		try {
			// SELECT usado para buscar todas as disciplinas/notas que pertencem ao RGM informado
			String sql = "SELECT id_nota, disciplina, semestre, nota, faltas, fk_rgm "
					+ "FROM tb_notas "
					+ "WHERE fk_rgm = ? AND disciplina = ? AND semestre = ?";

			ps = conn.prepareStatement(sql);

			ps.setString(1, rgm);
			ps.setString(2, disciplina);
			ps.setString(3, semestre);

			rs = ps.executeQuery();

			// Se encontrou a combinação RGM + disciplina + semestre, cria o objeto Nota
			if (rs.next()) {
				// Cria um objeto Nota para guardar os dados encontrados no ResultSet
				Nota nota = new Nota();

				nota.setIdNota(rs.getInt("id_nota"));
				nota.setDisciplina(rs.getString("disciplina"));
				nota.setSemestre(rs.getString("semestre"));
				nota.setNota(rs.getString("nota"));
				nota.setFaltas(rs.getInt("faltas"));
				nota.setRgm(rs.getString("fk_rgm"));

				return nota;
			}

			return null;

		} catch(Exception e) {
			throw new Exception("ERRO AO CONSULTAR NOTA: " + e.getMessage());
		}
	}
	// ===================== FIM: CONSULTAR NOTA =====================

	// ===================== INÍCIO: EXCLUIR ALUNO =====================
	// A exclusão segue essa ordem por causa das chaves estrangeiras: notas -> curso -> aluno.
	public void excluirAluno(String rgm) throws Exception {
		try {
			String sqlNotas = "DELETE FROM tb_notas WHERE fk_rgm = ?";
			ps = conn.prepareStatement(sqlNotas);
			ps.setString(1, rgm);
			ps.executeUpdate();

			String sqlCurso = "DELETE FROM tb_curso WHERE fk_rgm = ?";
			ps = conn.prepareStatement(sqlCurso);
			ps.setString(1, rgm);
			ps.executeUpdate();

			String sqlAluno = "DELETE FROM tb_aluno WHERE pk_rgm = ?";
			ps = conn.prepareStatement(sqlAluno);
			ps.setString(1, rgm);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new Exception("ERRO AO EXCLUIR ALUNO: " + e.getMessage());
		}
	}
	// ===================== FIM: EXCLUIR ALUNO =====================

	// ===================== INÍCIO: EXCLUIR NOTA =====================
	// Remove somente uma nota, sem apagar o cadastro do aluno nem o curso.
	public void excluirNota(String rgm, String disciplina, String semestre) throws Exception {
		try {
			String sqlNota = "DELETE FROM tb_notas "
					+ "WHERE fk_rgm = ? AND disciplina = ? AND semestre = ?";

			ps = conn.prepareStatement(sqlNota);

			ps.setString(1, rgm);
			ps.setString(2, disciplina);
			ps.setString(3, semestre);

			ps.executeUpdate();

		} catch(Exception e) {
			throw new Exception("ERRO AO EXCLUIR NOTA: " + e.getMessage());
		}
	}
	// ===================== FIM: EXCLUIR NOTA =====================

	// ===================== INÍCIO: CONSULTAR NOTAS POR SEMESTRE =====================
	// Mantido para consultas que ainda precisem filtrar as notas por semestre.
	public List<Nota> consultarNotasPorSemestre(String rgm, String semestre) throws Exception {
		// Lista que vai guardar todas as notas encontradas no SELECT
		List<Nota> lista = new ArrayList<Nota>();

		try {
			String sql = "SELECT id_nota, disciplina, semestre, nota, faltas, fk_rgm "
					+ "FROM tb_notas "
					+ "WHERE fk_rgm = ? AND semestre = ?";

			ps = conn.prepareStatement(sql);

			ps.setString(1, rgm);
			ps.setString(2, semestre);

			rs = ps.executeQuery();

			// O while é usado porque podem existir várias notas no mesmo semestre
			// O while percorre todos os registros encontrados, pois o aluno pode ter várias disciplinas
			// Enquanto houver registros no ResultSet, o laço continua montando objetos Nota
			while (rs.next()) {
				Nota nota = new Nota();

				nota.setIdNota(rs.getInt("id_nota"));
				nota.setDisciplina(rs.getString("disciplina"));
				nota.setSemestre(rs.getString("semestre"));
				nota.setNota(rs.getString("nota"));
				nota.setFaltas(rs.getInt("faltas"));
				nota.setRgm(rs.getString("fk_rgm"));

				// Cada nota encontrada é adicionada na lista para depois aparecer no boletim
				// Adiciona cada nota encontrada na lista que será devolvida para a tela
				// Adiciona a nota montada na lista que será retornada para a tela
				lista.add(nota);
			}

			return lista;

		} catch(Exception e) {
			throw new Exception("ERRO AO CONSULTAR NOTAS DO SEMESTRE: " + e.getMessage());
		}
	}
	// ===================== FIM: CONSULTAR NOTAS POR SEMESTRE =====================


	// ===================== INÍCIO: CONSULTAR NOTAS DO BOLETIM =====================
	// Usado no boletim novo para trazer todas as notas do aluno apenas pelo RGM.
	public List<Nota> consultarNotasBoletim(String rgm) throws Exception {
		List<Nota> lista = new ArrayList<Nota>();

		try {
			// SELECT usado para buscar todas as disciplinas/notas que pertencem ao RGM informado
			String sql = "SELECT id_nota, disciplina, semestre, nota, faltas, fk_rgm "
					+ "FROM tb_notas "
					+ "WHERE fk_rgm = ? "
					+ "ORDER BY semestre, disciplina"; // ordena o boletim para ficar mais organizado // organiza o resultado por semestre e disciplina

			ps = conn.prepareStatement(sql);
			ps.setString(1, rgm);

			rs = ps.executeQuery();

			// O while percorre todos os registros encontrados, pois o aluno pode ter várias disciplinas
			while (rs.next()) {
				Nota nota = new Nota();

				nota.setIdNota(rs.getInt("id_nota"));
				nota.setDisciplina(rs.getString("disciplina"));
				nota.setSemestre(rs.getString("semestre"));
				nota.setNota(rs.getString("nota"));
				nota.setFaltas(rs.getInt("faltas"));
				nota.setRgm(rs.getString("fk_rgm"));

				// Adiciona cada nota encontrada na lista que será devolvida para a tela
				lista.add(nota);
			}

			return lista;

		} catch(Exception e) {
			throw new Exception("ERRO AO CONSULTAR BOLETIM: " + e.getMessage());
		}
	}
	// ===================== FIM: CONSULTAR NOTAS DO BOLETIM =====================

}
