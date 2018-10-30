package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private ListDataModel<Produto> itensProduto;
	private Produto produto;
	
	//getters and setters
	public ListDataModel<Produto> getItensProduto() {
		return itensProduto;
	}
	public void setItensProduto(ListDataModel<Produto> itensProduto) {
		this.itensProduto = itensProduto;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@PostConstruct
	public void preparaPesquisaP() {
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			ArrayList<Produto> lista;
			lista = pdao.listar();
			itensProduto = new ListDataModel<Produto>(lista);
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.errMensagem(e.getMessage());
		}
	}
}
