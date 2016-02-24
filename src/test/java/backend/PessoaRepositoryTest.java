package backend;

import backend.model.Pessoa;
import backend.repository.PessoaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * Created by adriano on 23/02/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Deploy.class})
public class PessoaRepositoryTest {

    @Resource private PessoaRepository repository;

    @Test
    public void deveSalvar(){
        Pessoa pessoa = new Pessoa(1, "Teste");
        repository.save(pessoa);

        Assert.assertTrue(pessoa.getId() != null);
    }

    @Test
    public void deveEditar(){
        deveSalvar();

        Pessoa pessoa = repository.findOne(1L);
        pessoa.setNome("Mudou");
        repository.save(pessoa);

        pessoa = repository.findOne(1L);
        String nome = pessoa.getNome();

        Assert.assertTrue(pessoa.getId() != null);
        Assert.assertTrue(nome != null && nome.equals("Mudou"));
    }

    @Test
    public void deveRemover() {
        deveSalvar();

        Pessoa pessoa = repository.findOne(1L);
        repository.delete(pessoa);
        pessoa = repository.findOne(1L);

        Assert.assertTrue(pessoa == null);
    }

    @Test
    public void deveListar(){
        deveSalvar();

        Collection<Pessoa> pessoas = (Collection<Pessoa>)repository.findAll();
        Assert.assertTrue(!pessoas.isEmpty());
    }


}
