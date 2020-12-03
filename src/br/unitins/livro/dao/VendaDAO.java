package br.unitins.livro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.unitins.model.ebooks.ItemVenda;
import br.unitins.model.ebooks.Venda;

public class VendaDAO implements DAO<Venda> {

	@Override
	public void inserir(Venda obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("venda ");
		sql.append("  (data_venda, id_usuario) ");
		sql.append("VALUES ");
		sql.append("  ( current_timestamp, ?) ");
		PreparedStatement stat = null;

		try {
			// Este statement retorna a chave primaria gerada pelo banco de dados
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setInt(1, obj.getUsuario().getId());
			stat.execute();
			
			// obter a chave primaria gerada pelo banco de dados
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next())
				obj.setId(rs.getInt("id"));
			
			// salvando os itens de venda
			for (ItemVenda itemVenda : obj.getListaItemVenda()) {
				// se der algum problema
				if (!inserirItemVenda(itemVenda, conn, obj.getId())) {
					new SQLException("Erro ao inserir um item de venda");
				}
			}
			
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
	
	private boolean inserirItemVenda(ItemVenda itemVenda, Connection conn, Integer idVenda) {
		boolean retorno = true;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("item_venda ");
		sql.append("  (preco, id_midia, id_venda) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?) ");
		
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setDouble(1, itemVenda.getPreco());
			stat.setDouble(2, itemVenda.getLivro().getId());
			stat.setDouble(3, idVenda);
			stat.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			retorno  = false;
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}
		}
		return retorno;
	}

	@Override
	public void alterar(Venda obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Venda obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Venda> obterTodos() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venda obterUm(Venda obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
