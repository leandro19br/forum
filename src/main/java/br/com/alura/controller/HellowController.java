package br.com.alura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @project forum
 * Created by Leandro Saniago on 27/10/2021 - 11:05.
 */
@Controller
public class HellowController {

    @RequestMapping("/")
    @ResponseBody//devolve o conteudo direto no navegador
    public String hello(){
        return "Hello Word";
    }


}
