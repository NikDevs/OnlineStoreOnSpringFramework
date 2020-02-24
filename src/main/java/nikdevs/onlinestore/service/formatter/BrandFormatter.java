package nikdevs.onlinestore.service.formatter;

import nikdevs.onlinestore.service.interfaces.BrandService;
import nikdevs.onlinestore.service.model.BrandRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class BrandFormatter implements Formatter<BrandRepr> {

    private BrandService brandService;

    @Autowired
    public void setRoleService(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public BrandRepr parse(String s, Locale locale) throws ParseException {
        return brandService.findById(Integer.parseInt(s));
    }

    @Override
    public String print(BrandRepr brandRepr, Locale locale) {
        return Integer.toString(brandRepr.getId());
    }
}
