package br.com.zup.proposta.cartao.biometria;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface BiometriaRepository extends CrudRepository<Biometria, UUID>{
}