package br.com.alura.config.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @project forum
 * Created by Leandro Saniago on 28/10/2021 - 19:36.
 * calsse para tratamento de erro de validação de form
 */
@RestControllerAdvice
public class ErroValidacaoHandler {

    //atributo que auxilia na internacionalização da menssagem de erro
    private MessageSource messageSource;

    @Autowired
    public ErroValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //avisa o Spring para Spring que quando tiveruma exeption do tipo MethodArgumentNotValidException deve ser interceptado
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)//devolve status de erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroFormDTO> handle(MethodArgumentNotValidException ex){
        List<ErroFormDTO> erroFormDTO = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroFormDTO erro = new ErroFormDTO(e.getField(),message);
            erroFormDTO.add(erro);
        });

        return erroFormDTO;

    }

}
