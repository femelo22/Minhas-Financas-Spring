package br.com.luiz.resources;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.dto.UserAuthenticatedDTO;
import br.com.luiz.entities.dto.UsuarioDTO;
import br.com.luiz.service.LancamentoService;
import br.com.luiz.service.UsuarioService;
import br.com.luiz.service.exception.ErroAutenticacaoException;
import br.com.luiz.service.exception.RegraNegocioException;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private LancamentoService lancamentoService;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity salvar (@RequestBody UsuarioDTO objDTO) {
		
		Usuario usuario = this.userService.getUserDto(objDTO);
		
		try {
			Usuario usuarioSalvo = this.userService.salvarUsuario(usuario);
			
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/autenticar")
	public ResponseEntity autenticar (@RequestBody  UserAuthenticatedDTO objDTO) {
		try {
			Usuario usuario = this.userService.autenticar(objDTO.getEmail(), objDTO.getSenha());
			
			return ResponseEntity.ok().body(usuario);
			
		} catch (ErroAutenticacaoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping("{id}/saldo")
	public ResponseEntity obterSaldo(@PathVariable Integer id) {
		
		this.userService.buscarPorId(id);
		
		BigDecimal saldo = this.lancamentoService.obterSaldoPorUsuario(id);
		
		return ResponseEntity.ok().body(saldo);
	}

}
