package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domain.Taco;

@Repository
public interface JpaTacoRepository extends CrudRepository<Taco, Long> {
}
