package br.com.luiz.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luiz.entities.Lancamento;
import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.dto.LancamentoDTO;
import br.com.luiz.entities.enums.StatusLancamento;
import br.com.luiz.entities.enums.TipoLancamento;
import br.com.luiz.repository.LancamentoRepository;
import br.com.luiz.repository.UsuarioRepository;
import br.com.luiz.service.exception.RegraNegocioException;

@Service
public class LancamentoService {

	@Autowired
	LancamentoRepository repository;
	
	@Autowired
	UsuarioRepository userRepository;
	

	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		this.validar(lancamento);
		return this.repository.save(lancamento);
	}
	
	
	
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		//Valida que o lançamento tem que ter um ID, se não lança uma exceção
		Objects.requireNonNull(lancamento.getId());
		this.validar(lancamento);
		return this.repository.save(lancamento);
	}
	
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		this.repository.delete(lancamento);
	}
	
	
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		 //Faz a busca baseado nos filtros enviados
		Example example = Example.of(lancamentoFiltro,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		
		return this.repository.findAll(example);
	}
	
	
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatusLancamento(status);
		this.atualizar(lancamento);
	}
	
	
	public void validar(Lancamento lancamento) {
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Descrição do lançamento deve ser preenchida.");
		}
		
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Preencha um mês válido.");
		}
		
		if(lancamento.getAno() == null) {
			throw new RegraNegocioException("Preencha um ano válido.");
		}
		
		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("Usuário inválido.");
		}
		
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um valor válido;");
		}
		
	}
	
	
	public Lancamento converterDto(LancamentoDTO objDto) {
		
		Lancamento lancamento = new Lancamento();
		
		lancamento.setDescricao(objDto.getDescricao());
		lancamento.setAno(objDto.getAno());
		lancamento.setMes(objDto.getAno());
		lancamento.setValor(objDto.getValor());
		
		Usuario usuario = this.userRepository.findById(objDto.getIdUsuario())
				.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
		
		lancamento.setUsuario(usuario);
		lancamento.setTipoLancamento(TipoLancamento.valueOf(objDto.getTipo()));
		lancamento.setStatusLancamento(StatusLancamento.valueOf(objDto.getStatus()));
		
		return lancamento;
	}
	
	
	
	
}
