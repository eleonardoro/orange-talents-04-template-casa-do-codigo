package br.com.zup.proposta.validacoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
 class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		if (!fieldErrors.isEmpty()) {
			fieldErrors.forEach(e -> {
				String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
				ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
				dto.add(erro);
			});
		} else {
			List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();

			allErrors.forEach(e -> {
				String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
				ErroDeFormularioDto erro = new ErroDeFormularioDto("Quantidade", mensagem);
				dto.add(erro);
			});
		}

		return dto;
	}

}