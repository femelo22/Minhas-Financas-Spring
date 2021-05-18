package br.com.luiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luiz.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public boolean existsByEmail(String email);
	
	public Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
