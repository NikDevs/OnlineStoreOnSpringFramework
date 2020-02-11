package nikdevs.onlinestore.config;

import nikdevs.onlinestore.persist.formatter.RoleFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    private RoleFormatter roleFormatter;

    @Autowired
    public void setRoleFormatter(RoleFormatter roleFormatter) {
        this.roleFormatter = roleFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(roleFormatter);
    }
}
