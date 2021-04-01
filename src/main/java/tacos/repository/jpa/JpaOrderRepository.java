package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domain.TacoOrder;

@Repository
public interface JpaOrderRepository extends CrudRepository<TacoOrder, Long> {
}
