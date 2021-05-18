package br.com.luiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luiz.entities.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>{

}
