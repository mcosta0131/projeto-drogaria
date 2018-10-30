package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private ListDataModel<Fabricante> itens;
	private Fabricante fabricante;

	// getters and setters
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ListDataModel<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fabricante> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void preparaPesquisa() {
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			ArrayList<Fabricante> lista;
			lista = fdao.listar();
			itens = new ListDataModel<Fabricante>(lista);
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.errMensagem(e.getMessage());
		}
	}

	public void novoFabricante() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);
			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);
			JSFUtil.addMensagem("Fabricante adicionado com sucesso !!");
		} catch (SQLException e) {
			JSFUtil.errMensagem(e.getMessage());
			e.printStackTrace();
		}
	}

	public void preparaFabricante() {
		fabricante = new Fabricante();
	}

	public void preparaExcluir() {
		fabricante = itens.getRowData();
	}

	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);

			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);
			JSFUtil.addMensagem("Fabricante Removido com sucesso !!");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.errMensagem(e.getMessage());
		}
	}

	public void preparaAtualiza() {
		fabricante = itens.getRowData();
	}

	public void atualiza() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);

			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);

			JSFUtil.addMensagem("Atualizado com sucesso");
		} catch (SQLException e) {
			JSFUtil.errMensagem(e.getMessage());
			e.printStackTrace();
		}
	}
}
