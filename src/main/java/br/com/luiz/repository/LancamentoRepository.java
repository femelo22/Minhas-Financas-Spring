package br.com.luiz.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.luiz.entities.Lancamento;
import br.com.luiz.entities.enums.TipoLancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>{

	@Query(value = "select sum(l.valor) from Lancamento l join l.usuario u "
			+ " where u.id = :idUsuario and l.tipoLancamento = :tipoLancamento group by u")
	BigDecimal obterSaldoPorTipoLancamentoEUsuario(
			@Param("idUsuario") Integer idUsuario,
			@Param("tipoLancamento") TipoLancamento tipoLancamento);
}
