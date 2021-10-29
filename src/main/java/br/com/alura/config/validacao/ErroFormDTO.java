package br.com.alura.config.validacao;

/**
 * @project forum
 * Created by Leandro Saniago on 28/10/2021 - 19:42.
 * classe DTO que vai representar o erro de validação
 */
public class ErroFormDTO {

    private String campo;
    private String erro;

    public ErroFormDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
