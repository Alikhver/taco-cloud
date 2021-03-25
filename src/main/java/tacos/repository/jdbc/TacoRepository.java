package tacos.repository.jdbc;

import tacos.domain.Taco;

public interface TacoRepository {

    public Taco save(Taco taco);

}
