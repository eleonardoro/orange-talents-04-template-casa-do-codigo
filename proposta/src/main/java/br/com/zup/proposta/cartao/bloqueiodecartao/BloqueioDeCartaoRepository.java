package br.com.zup.proposta.cartao.bloqueiodecartao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface BloqueioDeCartaoRepository extends CrudRepository<BloqueioDeCartao, UUID> {
}