package backend.controller;

import backend.model.Pessoa;
import backend.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by adrianobrito on 14/10/15.
 */
@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Resource
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Pessoa save(@RequestBody Pessoa pessoa) {
        pessoa = pessoaRepository.save(pessoa);
        return pessoa;
    }

    @RequestMapping(value="{pessoa.id}",method = RequestMethod.PUT)
    public Pessoa update(@RequestBody Pessoa pessoa) {
        pessoa = pessoaRepository.save(pessoa);
        return pessoa;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pessoa findById(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findOne(id);
        return pessoa;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        pessoaRepository.delete(id);
    }

}