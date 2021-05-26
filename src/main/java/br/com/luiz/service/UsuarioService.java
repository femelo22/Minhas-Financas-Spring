package br.com.luiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.dto.UsuarioDTO;
import br.com.luiz.repository.UsuarioRepository;
import br.com.luiz.service.exception.ErroAutenticacaoException;
import br.com.luiz.service.exception.RegraNegocioException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	
	public Usuario autenticar(String email, String senha) {
		Usuario user = this.repo.findByEmailAndSenha(email, senha)
				.orElseThrow(() -> new ErroAutenticacaoException("Usuário não encontrado!"));
		
		return user;
	}

	
	@Transactional(readOnly = true)
	public Usuario salvarUsuario(Usuario usuario) {
		this.validarEmail(usuario.getEmail());
		
		return repo.save(usuario);
	}
	

	public void validarEmail(String email) {
		
		boolean existe = this.repo.existsByEmail(email);
		
		if(existe) {
			throw new RegraNegocioException("Usuário já cadastrado com esse email");
		}
		
	}
	
	public Usuario getUserDto(UsuarioDTO userDto) {
		return new Usuario(userDto);
	}
	
	public Usuario buscarPorId(Integer id) {
		return this.repo.findById(id).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));
	}

}
