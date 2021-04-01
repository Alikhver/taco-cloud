package tacos.repository.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInserter;

    private final SimpleJdbcInsert orderTacoInserter;

    private final ObjectMapper objectMapper;


    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public TacoOrder save(TacoOrder order) {

        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }

    private long saveOrderDetails(TacoOrder order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values =
                objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());

        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();
        return orderId;
    }

    public void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }


}
