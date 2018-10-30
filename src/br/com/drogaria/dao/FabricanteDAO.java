package br.com.drogaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	// Metodo que executa comandos sql(INSERT)
	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");

		java.sql.Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		comando.executeUpdate();
	}

	// Metodo que executa comandos sql(DELETE)
	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		java.sql.Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		comando.executeUpdate();
	}

	// Metodo que executa comandos sql(UPDATE)
	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		java.sql.Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();
	}

	// Metodo que executa comandos sql(SELECT somente um registro)
	public Fabricante buscarCodigo(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo,descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		java.sql.Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;
		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	// Metodo que executa comandos sql(SELECT lista de registros)
	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo,descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		java.sql.Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	public ArrayList<Fabricante> buscaDescricao(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo,descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ?");
		sql.append("ORDER BY descricao ASC ");

		java.sql.Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}
		return lista;
	}

	// Metodo main para testar metodos no banco
	public static void main(String[] args) {

		// ------------------- Teste de inclusao de registro (INSERT)
		// ------------------------------------

		/*
		 * Fabricante f1 = new Fabricante(); Fabricante f2 = new Fabricante();
		 * 
		 * f1.setDescricao("Descricao01"); f2.setDescricao("Descricao02");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.salvar(f1); fdao.salvar(f2);
		 * System.out.println("Os Fabricantes foram salvos com sucesso");
		 * 
		 * }catch (SQLException e) { e.printStackTrace();
		 * System.out.println("Erro ao salvar " + e); }
		 */

		// ------------------- Teste de exclusao de registro (DELETE)
		// ------------------------------------

		/*
		 * Fabricante fDelete = new Fabricante(); fDelete.setCodigo(1L);
		 * 
		 * Fabricante fDelete2 = new Fabricante(); fDelete2.setCodigo(2L);
		 * 
		 * FabricanteDAO fDao = new FabricanteDAO();
		 * 
		 * 
		 * try { fDao.excluir(fDelete); fDao.excluir(fDelete2);
		 * System.out.println("Fabricantes excluidos com sucesso"); } catch
		 * (SQLException e) {
		 * System.out.println("Não foi possivel excluir fabricantes " + e ); }
		 */

		// ------------------- Teste de atualizacao de registro (UPDATE)
		// -------------------------------------------

		/*
		 * Fabricante fUpdate = new Fabricante(); fUpdate.setCodigo(3L);
		 * fUpdate.setDescricao("Descricao 03");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try { fdao.editar(fUpdate);
		 * System.out.println("Atualizado com sucesso"); } catch (SQLException
		 * e) {
		 * 
		 * System.out.println("Erro ao atualizar registro" + e ); }
		 */
		// ------------------- Teste de consulta de registro (SELECT)
		// -----------------------------------------------

		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(3L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fdao.buscarCodigo(f1);
		 * System.out.println("Resultado 1 " + f3);
		 * 
		 * } catch (SQLException e) { System.out.println(e); }
		 */
		// ------------------- Teste de consulta de lista de registros (SELECT)
		// ------------------------------------

		Fabricante f1 = new Fabricante();
		f1.setDescricao("2");
		FabricanteDAO fdao = new FabricanteDAO();

		try {
			ArrayList<Fabricante> lista = fdao.buscaDescricao(f1);
			for (Fabricante f : lista) {
				System.out.println(f);
			}
			System.out.println("Lista retornada com sucesso !!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
