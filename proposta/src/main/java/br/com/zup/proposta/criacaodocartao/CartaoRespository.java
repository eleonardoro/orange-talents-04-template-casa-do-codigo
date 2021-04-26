package br.com.zup.proposta.criacaodocartao;

import org.springframework.data.repository.CrudRepository;

public interface CartaoRespository extends CrudRepository<Cartao, String> {
}