package nikdevs.onlinestore.persist.formatter;

import nikdevs.onlinestore.persist.model.Role;
import nikdevs.onlinestore.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class RoleFormatter implements Formatter<Role> {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role parse(String s, Locale locale) throws ParseException {
        return roleService.findById(Integer.parseInt(s));
    }

    @Override
    public String print(Role role, Locale locale) {
        return Integer.toString(role.getId());
    }
}
