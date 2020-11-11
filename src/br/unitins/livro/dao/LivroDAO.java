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
		sql.append(" (titulo, editora, idioma, categoria, ano, numero_paginas) ");
		sql.append("values ");
		sql.append(" (?, ?, ?, ?, ?, ?) ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setString(1, obj.getTitulo());
			stat.setString(2, obj.getEditora());
			stat.setString(3, obj.getIdioma());
			stat.setString(4, obj.getCategoria());
			stat.setString(5, obj.getAno());
			stat.setString(6, obj.getNumeroPaginas());

			// ternario java

			// convertendo um obj LocalDate para sql.Date

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
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Livro obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Livro> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Livro> listaUsuario = new ArrayList<Livro>();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from livro ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("id"));
				Date data = rs.getDate("data_nascimento");
				
				// listaUsuario.add(usuario);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do usuario.");
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

		return listaUsuario;
	}

	@Override
	public Livro obterUm(Livro obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
