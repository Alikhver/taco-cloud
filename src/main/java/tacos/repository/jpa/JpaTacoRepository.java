package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Taco;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {
}
