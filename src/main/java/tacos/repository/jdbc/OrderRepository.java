package tacos.repository.jdbc;

import tacos.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
