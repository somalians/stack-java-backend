package backend.model;

/**
 * Created by adrianobrito on 14/10/15.
 */
public class Pessoa {

    private final long id;
    private final String nome;

    public Pessoa(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
