package br.com.luiz.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity salvar(@RequestBody LancamentoDTO objDto) {

		try {
			Lancamento lancamento = this.service.converterDto(objDto);

			lancamento = this.service.salvar(lancamento);

			return ResponseEntity.ok(lancamento);

		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	

}
