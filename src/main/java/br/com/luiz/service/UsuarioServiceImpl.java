package br.com.luiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luiz.entities.Usuario;
import br.com.luiz.repository.UsuarioRepository;
import br.com.luiz.service.exception.RegraNegocioException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = this.repo.existsByEmail(email);
		
		if(existe) {
			throw new RegraNegocioException("Usuário já cadastrado com esse email");
		}
		
	}

}
