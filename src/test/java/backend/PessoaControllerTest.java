package backend;

import backend.model.Pessoa;
import backend.repository.PessoaRepository;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

/**
 * Created by adrianobrito on 24/02/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Deploy.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class PessoaControllerTest {

    @Resource
    private PessoaRepository pessoaRepository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {

        Pessoa mickey = new Pessoa(1, "Mickey");
        Pessoa minnie = new Pessoa(2, "Minnie");
        Pessoa pluto = new Pessoa(3, "Pluto"); // no final cachorro tambem é gente

        pessoaRepository.deleteAll();
        pessoaRepository.save(Arrays.asList(mickey, minnie, pluto));

        RestAssured.port = port;
    }

    @After
    public void clean(){
        pessoaRepository.deleteAll();
    }

    @Test
    public void shouldGetPessoa(){
        Integer id = 1;
        String nome = "Mickey";

        when().get("/pessoas/{id}", id)
              .then().statusCode(HttpStatus.SC_OK)
                     .body("id", is(id))
                     .body("nome", is(nome));
    }

    @Test
    public void shouldSavePessoa(){
        Pessoa donald = new Pessoa(4, "Donald");
        given().contentType(ContentType.JSON)
               .body(donald)
        .when().post("/pessoas")
        .then().statusCode(HttpStatus.SC_OK)
               .body("id", is(donald.getId().intValue()))
               .body("nome", is(donald.getNome()));
    }

    @Test
    public void shouldUpdatePessoa(){
        Pessoa mickey = new Pessoa(1, "Mickey Mouse");
        Long mickeyId = mickey.getId();

        given().contentType(ContentType.JSON)
               .body(mickey)
        .when().put("/pessoas/{mickeyId}", mickeyId)
        .then().statusCode(HttpStatus.SC_OK)
                     .body("id", is(mickeyId.intValue()))
                     .body("nome", is(mickey.getNome()));
    }

    @Test
    public void shouldDeletePessoa(){
        Integer id = 1;
        when().delete("/pessoas/{id}", id)
              .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }


}
