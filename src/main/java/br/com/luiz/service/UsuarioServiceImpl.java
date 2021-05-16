package br.com.luiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luiz.entities.Usuario;
import br.com.luiz.repository.UsuarioRepository;
import br.com.luiz.service.exception.ErroAutenticacaoException;
import br.com.luiz.service.exception.RegraNegocioException;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public Usuario autenticar(String email, String senha) {
		Usuario user = this.repo.findByEmail(email, senha)
				.orElseThrow(() -> new ErroAutenticacaoException("Usuário não encontrado!"));
		
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario salvarUsuario(Usuario usuario) {
		this.validarEmail(usuario.getEmail());
		
		return repo.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = this.repo.existsByEmail(email);
		
		if(existe) {
			throw new RegraNegocioException("Usuário já cadastrado com esse email");
		}
		
	}

}
