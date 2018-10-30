package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Ignore
	public void salvar() throws SQLException{
		Produto p = new Produto();
		p.setDescricao("Novalgina");
		p.setPreco(2.00);
		p.setQuantidade(13L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(51L);
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}
	
	@Ignore
	public void listar()throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista){
			System.out.println("Codigo: " + p.getCodigo());
			System.out.println("Descrição: " + p.getDescricao());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			
			System.out.println("Codigo do Fabricante " + p.getFabricante().getCodigo());
			System.out.println("Descrição do Fabricante " + p.getFabricante().getDescricao());
			
		}
	}
	@Ignore
	public void delete() throws SQLException{
		Produto pDelete = new Produto();
		pDelete.setCodigo(2L);
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.excluir(pDelete);
	}
	
	@Test
	public void atualiza() throws SQLException{
		Produto pAtualiza = new Produto();
		pAtualiza.setCodigo(12L);
		pAtualiza.setDescricao("Tylenol Novo");
		pAtualiza.setPreco(20.00);
		pAtualiza.setQuantidade(400L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(51L);
		
		pAtualiza.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(pAtualiza);
	}
}

