package tacos.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domain.Order;

@Repository
public interface JpaOrderRepository extends CrudRepository<Order, Long> {
}
