package br.com.luiz;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.luiz.entities.Lancamento;
import br.com.luiz.entities.Usuario;
import br.com.luiz.entities.enums.StatusLancamento;
import br.com.luiz.entities.enums.TipoLancamento;
import br.com.luiz.service.LancamentoService;
import br.com.luiz.service.UsuarioService;

@SpringBootApplication
@EnableWebMvc
public class MinhasFinancasApplication extends SpringBootServletInitializer implements CommandLineRunner, WebMvcConfigurer {

	@Autowired
	UsuarioService userService;

	@Autowired
	LancamentoService lancamentoService;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MinhasFinancasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usu1 = new Usuario();
		usu1.setId(null);
		usu1.setEmail("luiz_123jfmg@hotmail.com");
		usu1.setNome("Luiz Fernando");
		usu1.setSenha("123123123");

		Usuario usu2 = new Usuario();
		usu2.setId(null);
		usu2.setEmail("Winiciusfmg@gmail.com");
		usu2.setNome("Winicius Cosme");
		usu2.setSenha("u8bn3ax4");

		this.userService.salvarUsuario(usu1);
		this.userService.salvarUsuario(usu2);

		
		Lancamento lan1 = new Lancamento();
		lan1.setId(null);
		lan1.setAno(2021);
		lan1.setMes(01);
		lan1.setDescricao("Pagamento Consultoria");
		lan1.setStatusLancamento(StatusLancamento.PENDENTE);
		lan1.setTipoLancamento(TipoLancamento.RECEITA);
		lan1.setUsuario(usu1);
		lan1.setValor(BigDecimal.valueOf(300));

		
		Lancamento lan2 = new Lancamento();
		lan2.setId(null);
		lan2.setAno(2021);
		lan2.setMes(05);
		lan2.setDescricao("Compra mercado");
		lan2.setStatusLancamento(StatusLancamento.PENDENTE);
		lan2.setTipoLancamento(TipoLancamento.DESPESA);
		lan2.setUsuario(usu2);
		lan2.setValor(BigDecimal.valueOf(150.99));

		this.lancamentoService.salvar(lan1);
		this.lancamentoService.salvar(lan2);

	}

}
