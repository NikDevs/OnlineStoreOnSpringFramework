package nikdevs.onlinestore.config;

import nikdevs.onlinestore.service.formatter.BrandFormatter;
import nikdevs.onlinestore.service.formatter.CategoryFormatter;
import nikdevs.onlinestore.service.formatter.RoleFormatter;
import nikdevs.onlinestore.service.formatter.SizeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    private RoleFormatter roleFormatter;
    private CategoryFormatter categoryFormatter;
    private BrandFormatter brandFormatter;
    private SizeFormatter sizeFormatter;

    @Autowired
    public void setRoleFormatter(RoleFormatter roleFormatter) {
        this.roleFormatter = roleFormatter;
    }

    @Autowired
    public void setCategoryFormatter(CategoryFormatter categoryFormatter) {
        this.categoryFormatter = categoryFormatter;
    }

    @Autowired
    public void setBrandFormatter(BrandFormatter brandFormatter) {
        this.brandFormatter = brandFormatter;
    }

    @Autowired
    public void setSizeFormatter(SizeFormatter sizeFormatter) {
        this.sizeFormatter = sizeFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(roleFormatter);
        registry.addFormatter(categoryFormatter);
        registry.addFormatter(brandFormatter);
        registry.addFormatter(sizeFormatter);
    }
}
