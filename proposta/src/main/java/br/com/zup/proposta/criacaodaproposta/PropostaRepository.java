package br.com.zup.proposta.criacaodaproposta;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, UUID> {

	List<Proposta> findByEstadoDaPropostaIn(List<EstadoDaProposta> estadosDaProposta);

}