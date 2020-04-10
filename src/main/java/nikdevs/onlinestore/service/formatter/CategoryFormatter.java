package nikdevs.onlinestore.service.formatter;

import nikdevs.onlinestore.service.interfaces.CategoryService;
import nikdevs.onlinestore.service.repr.CategoryRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class CategoryFormatter implements Formatter<CategoryRepr> {

    private CategoryService categoryService;

    @Autowired
    public void setRoleService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryRepr parse(String s, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(s));
    }

    @Override
    public String print(CategoryRepr categoryRepr, Locale locale) {
        return Long.toString(categoryRepr.getId());
    }
}
