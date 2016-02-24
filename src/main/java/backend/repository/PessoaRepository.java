package backend.repository;

import backend.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by adriano on 24/02/16.
 */
@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{}
