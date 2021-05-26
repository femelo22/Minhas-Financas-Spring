package br.com.luiz.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luiz.entities.Lancamento;
import br.com.luiz.entities.dto.LancamentoDTO;
import br.com.luiz.service.LancamentoService;
import br.com.luiz.service.exception.RegraNegocioException;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoResource {

	@Autowired
	LancamentoService service;

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

	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody LancamentoDTO objDto) {
		try {
			Lancamento lancamento = this.service.obterPorId(id);

			lancamento.setId(id);

			this.service.atualizar(lancamento);

			return new ResponseEntity(HttpStatus.NO_CONTENT);

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

			return ResponseEntity.ok(lancamento);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body("Lancamento não encontrado na base");
		}
	}

}
