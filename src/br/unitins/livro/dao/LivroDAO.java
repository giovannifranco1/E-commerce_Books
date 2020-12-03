package br.unitins.livro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.application.ebooks.Util;
import br.unitins.model.ebooks.Livro;

public class LivroDAO implements DAO<Livro> {

	@Override
	public void inserir(Livro obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("insert into livro "); 
		sql.append(" (titulo, editora, idioma, categoria,numero_paginas, descricao, ano, preco) ");
		sql.append("values ");
		sql.append(" (?, ?, ?, ?, ?, ?, ?, ?) ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setString(1, obj.getTitulo());
			stat.setString(2, obj.getEditora());
			stat.setString(3, obj.getIdioma());
			stat.setString(4, obj.getCategoria());
			
			stat.setString(5, obj.getNumeroPaginas());
			stat.setString(6, obj.getDescricao());
			if (obj.getAno() != null)
				stat.setDate(7, Date.valueOf(obj.getAno()));
			else
				stat.setDate(7, null);
			stat.setDouble(8, obj.getPreco());
				
			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public void alterar(Livro obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario SET ");
		sql.append("  titulo = ?, ");
		sql.append("  editora = ?, ");
		sql.append("  idioma = ?, ");
		sql.append("  categoria = ?, ");
		sql.append("  ano = ?, ");
		sql.append("  paginas = ?, ");
		sql.append("  descricao = ? ");
		sql.append(" preco = ?");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getTitulo());
			stat.setString(2, obj.getEditora());
			stat.setString(3, obj.getIdioma());
			stat.setString(4, obj.getCategoria());
			if (obj.getAno() != null)
				stat.setDate(5, Date.valueOf(obj.getAno()));
			else
				stat.setDate(5, null);
			stat.setString(6, obj.getNumeroPaginas());
			stat.setString(7, obj.getDescricao());
			stat.setDouble(8, obj.getPreco());
			

			stat.execute();
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;


	}

	@Override
	public void excluir(Livro obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM livro WHERE id = ?");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public List<Livro> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Livro> listaLivro = new ArrayList<Livro>();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from livro ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setEditora(rs.getString("editora"));
				livro.setIdioma(rs.getString("idioma"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setNumeroPaginas(rs.getString("numero_paginas"));
				livro.setDescricao(rs.getString("descricao"));
				Date data = rs.getDate("ano");
				livro.setAno(data == null ? null : data.toLocalDate());
				livro.setPreco(rs.getDouble("preco"));
				listaLivro.add(livro);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaLivro;
	}

	@Override
	public Livro obterUm(Livro obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Livro> listaLivro = new ArrayList<Livro>();
		Livro livro = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  l.id, ");
		sql.append("  l.titulo, ");
		sql.append("  l.editora, ");
		sql.append("  l.idioma, ");
		sql.append("  l.categoria, ");
		sql.append("  l.numero_paginas, ");
		sql.append("  l.descricao, ");
		sql.append("  l.ano ");
		sql.append("  l.preco ");
		sql.append("FROM  ");
		sql.append("  livro l ");
		sql.append("WHERE l.id = ? ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setEditora(rs.getString("editora"));
				livro.setIdioma(rs.getString("idioma"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setNumeroPaginas(rs.getString("numero_paginas"));
				livro.setDescricao(rs.getString("descricao"));
				Date data = rs.getDate("ano");
				livro.setAno(data == null ? null : data.toLocalDate());
				livro.setPreco(rs.getDouble("preco"));
				listaLivro.add(livro);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do usuario.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em UsuarioDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return livro;
	}
	public List<Livro> obterListaMidia(Integer tipo, String filtro) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Livro> listaLivro = new ArrayList<Livro>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  livro.id, ");
		sql.append("  livro.titulo, ");
		sql.append("  livro.editora, ");
		sql.append("  livro.idioma, ");
		sql.append("  livro.categoria, ");
		sql.append("  livro.numero_paginas, ");
		sql.append("  livro.descricao, ");
		sql.append("  livro.ano ");
		sql.append("  livro.preco ");
		sql.append("FROM  ");
		sql.append("  livro ");
		sql.append("WHERE ");
		sql.append("  upper(livro.titulo) LIKE upper( ? ) ");
		sql.append("  AND upper(livro.categoria) LIKE upper( ? ) ");
		sql.append("ORDER BY livro.titulo ");
		

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, tipo == 1 ? "%"+ filtro +"%" : "%");
			stat.setString(2, tipo == 2 ? "%"+ filtro +"%" : "%");

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setEditora(rs.getString("editora"));
				livro.setIdioma(rs.getString("idioma"));
				livro.setCategoria(rs.getString("categoria"));
				livro.setNumeroPaginas(rs.getString("numero_paginas"));
				livro.setDescricao(rs.getString("descricao"));
				Date data = rs.getDate("ano");
				livro.setAno(data == null ? null : data.toLocalDate());
				livro.setPreco(rs.getDouble("preco"));
				listaLivro.add(livro);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("N�o foi possivel buscar os dados do midia.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em MidiaDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaLivro;
	}
}
