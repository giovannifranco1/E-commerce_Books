package br.unitins.livro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.application.ebooks.Util;
import br.unitins.model.ebooks.Livro;
import br.unitins.model.ebooks.Perfil;
import br.unitins.model.ebooks.Pessoa;

public class PessoaDAO implements DAO<Pessoa> {

	@Override
	public void inserir(Pessoa obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("insert into usuario");
		sql.append(" (nome, cpf, perfil, email, senha) ");
		sql.append("values ");
		sql.append(" (?, ?, ?, ?, ?) ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCpf());
			stat.setObject(3, (obj.getPerfil() == null ? null : obj.getPerfil().getId()));
			stat.setString(4, obj.getEmail());
			stat.setString(5, obj.getSenha());

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
	public void alterar(Pessoa obj) throws Exception {

	}

	@Override
	public void excluir(Pessoa obj) throws Exception {

	}

	@Override
	public List<Pessoa> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Pessoa> listaLivro = new ArrayList<Pessoa>();

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from usuario ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setSenha(rs.getString("senha"));

				listaLivro.add(pessoa);
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

		return listaLivro;
	}

	@Override
	public Pessoa obterUm(Pessoa obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
