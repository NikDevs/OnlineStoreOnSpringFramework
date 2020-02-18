package nikdevs.onlinestore.service.formatter;

import nikdevs.onlinestore.service.interfaces.RoleService;
import nikdevs.onlinestore.service.model.RoleRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class RoleFormatter implements Formatter<RoleRepr> {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleRepr parse(String s, Locale locale) throws ParseException {
        return roleService.findById(Integer.parseInt(s));
    }

    @Override
    public String print(RoleRepr roleRepr, Locale locale) {
        return Integer.toString(roleRepr.getId());
    }
}
