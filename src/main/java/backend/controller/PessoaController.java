package backend.controller;

import backend.model.Pessoa;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by adrianobrito on 14/10/15.
 */
@RestController
public class PessoaController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/pessoa")
    public Pessoa pessoa(@RequestParam(value="nome", defaultValue="Mundo Novo") String nome) {
        return new Pessoa(counter.incrementAndGet(), getAlgumaCoisa());
    }

    public String getAlgumaCoisa(){
        return "Novo metodo";
    }

}
