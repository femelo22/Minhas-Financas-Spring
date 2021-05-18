package br.com.luiz.entities.dto;

import br.com.luiz.entities.Usuario;

public class UsuarioDTO {
	
	private String email;
	
	private String nome;
	
	private String senha;
	
	public UsuarioDTO () {}
	
	public UsuarioDTO(Usuario user) {
		this.email = user.getEmail();
		this.nome = user.getNome();
		this.senha = user.getSenha();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
