package nikdevs.onlinestore.service.model;

import nikdevs.onlinestore.persist.model.Role;

import java.io.Serializable;
import java.util.Objects;

public class RoleRepr implements Serializable {

    private Integer id;
    private String name;
    private Long userCount;

    public RoleRepr() {
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public RoleRepr(Integer id, String name, Long userCount) {
        this.id = id;
        this.name = name;
        this.userCount = userCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleRepr roleRepr = (RoleRepr) o;
        return id.equals(roleRepr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name.replaceAll("ROLE_", "");
    }
}
