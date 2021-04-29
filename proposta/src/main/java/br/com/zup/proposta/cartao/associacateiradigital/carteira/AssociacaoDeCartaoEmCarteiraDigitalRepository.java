package br.com.zup.proposta.cartao.associacateiradigital.carteira;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.proposta.cartao.associacateiradigital.CarteirasDigitais;

public interface AssociacaoDeCartaoEmCarteiraDigitalRepository
		extends CrudRepository<AssociacaoDeCartaoEmCarteiraDigital, UUID> {

	List<AssociacaoDeCartaoEmCarteiraDigital> findByCartao_IdAndCarteira(String id, CarteirasDigitais carteira);
}