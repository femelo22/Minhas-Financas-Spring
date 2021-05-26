package br.com.luiz.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luiz.entities.Lancamento;
import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.dto.LancamentoDTO;
import br.com.luiz.service.LancamentoService;
import br.com.luiz.service.UsuarioServiceImpl;
import br.com.luiz.service.exception.RegraNegocioException;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoResource {

	@Autowired
	LancamentoService service;
	
	@Autowired
	UsuarioServiceImpl usuarioService;

	@PostMapping
	public ResponseEntity salvar(@RequestBody LancamentoDTO objDto) {

		try {
			Lancamento lancamento = this.service.converterDto(objDto);

			lancamento = this.service.salvar(lancamento);

			return new ResponseEntity(lancamento, HttpStatus.CREATED);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value="descricao", required = false) String descricao,
			@RequestParam(value="mes", required = false) Integer mes,
			@RequestParam(value="ano", required = false) Integer ano,
			@RequestParam("usuario") Integer idUsuario) {
		
		Lancamento lancamentoFiltro = new Lancamento();
		lancamentoFiltro.setDescricao(descricao);
		lancamentoFiltro.setMes(mes);
		lancamentoFiltro.setAno(ano);
		
		Usuario usuario = this.usuarioService.buscarPorId(idUsuario);
		
		lancamentoFiltro.setUsuario(usuario);
		
		
		List<Lancamento> lacamentos = this.service.buscar(lancamentoFiltro);
		
		return ResponseEntity.ok(lacamentos);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody LancamentoDTO objDto) {
		try {
			Lancamento lancamento = this.service.obterPorId(id);
			
			Lancamento lancamentoAtt = this.service.converterDto(objDto);
			
			lancamentoAtt.setId(id);

			this.service.atualizar(lancamentoAtt);

			return new ResponseEntity(lancamento, HttpStatus.OK);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body("Lancamento não encontrado na base");
		}
	}
	

	
	

	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable Integer id) {
		try {
			Lancamento lancamento = this.service.obterPorId(id);

			lancamento.setId(id);

			this.service.deletar(lancamento);

			return new ResponseEntity(HttpStatus.NO_CONTENT);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body("Lancamento não encontrado na base");
		}
	}
	

}
