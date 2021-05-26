package br.com.luiz;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luiz.entities.Lancamento;
import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.enums.StatusLancamento;
import br.com.luiz.entities.enums.TipoLancamento;
import br.com.luiz.service.LancamentoService;
import br.com.luiz.service.UsuarioServiceImpl;

@SpringBootApplication
public class MinhasFinancasApplication implements CommandLineRunner{

	@Autowired
	UsuarioServiceImpl userService;
	
	@Autowired
	LancamentoService lancamentoService;
	
	public static void main(String[] args) {
		SpringApplication.run(MinhasFinancasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		
		usuario.setId(null);
		usuario.setEmail("luiz_123jfmg@hotmail.com");
		usuario.setNome("Luiz Fernando");
		usuario.setSenha("123123123");
		
		this.userService.salvarUsuario(usuario);
		
		
		
		
		Lancamento lancamento = new Lancamento();
		
		lancamento.setId(null);
		lancamento.setAno(2021);
		lancamento.setMes(01);
		lancamento.setDescricao("Pagamento Ifood");
		lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
		lancamento.setTipoLancamento(TipoLancamento.DESPESA);
		lancamento.setUsuario(usuario);
		lancamento.setValor(BigDecimal.valueOf(300));
		
		this.lancamentoService.salvar(lancamento);
		
	}

}
