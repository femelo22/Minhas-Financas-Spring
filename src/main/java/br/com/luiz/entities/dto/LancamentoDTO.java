package br.com.luiz.entities.dto;

import java.math.BigDecimal;

public class LancamentoDTO {

	private String descricao;
	private Integer mes;
	private Integer ano;
	private BigDecimal valor;
	private Integer idUsuario;
	private String tipoLancamento;
	private String statusLancamento;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	public String getStatusLancamento() {
		return statusLancamento;
	}
	public void setStatusLancamento(String statusLancamento) {
		this.statusLancamento = statusLancamento;
	}

	
	
}
