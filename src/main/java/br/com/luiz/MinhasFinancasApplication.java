package br.com.luiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.luiz.entities.Usuario;
import br.com.luiz.service.UsuarioServiceImpl;

@SpringBootApplication
public class MinhasFinancasApplication implements CommandLineRunner{

	@Autowired
	UsuarioServiceImpl userService;
	
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
	}

}
