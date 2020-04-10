package nikdevs.onlinestore.service.repr;

import java.io.Serializable;
import java.util.Objects;

public abstract class StandartRepr implements Serializable {

    Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandartRepr that = (StandartRepr) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
