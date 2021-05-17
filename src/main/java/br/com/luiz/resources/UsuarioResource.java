package br.com.luiz.resources;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.dto.UsuarioDTO;
import br.com.luiz.service.UsuarioService;
import br.com.luiz.service.UsuarioServiceImpl;

@RestController
@RequestMapping(value = "/user")
public class UsuarioResource {
	
	@Autowired
	private UsuarioServiceImpl userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Usuario> salvar (@RequestBody UsuarioDTO objDTO) {
		
		Usuario user = this.retornarUsuarioDTO(objDTO);
		
		Usuario newUser = this.userService.salvarUsuario(user);
		
		return ResponseEntity.ok().body(newUser);
	}
	

	public Usuario retornarUsuarioDTO(UsuarioDTO usuario) {
		return modelMapper.map(usuario, Usuario.class);
	}

}
