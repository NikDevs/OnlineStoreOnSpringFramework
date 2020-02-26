package nikdevs.onlinestore.service.formatter;

import nikdevs.onlinestore.service.interfaces.SizeService;
import nikdevs.onlinestore.service.model.SizeRepr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class SizeReprFormatter implements Formatter<SizeRepr> {

    private SizeService sizeService;

    @Autowired
    public void setRoleService(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @Override
    public SizeRepr parse(String s, Locale locale) throws ParseException {
        return sizeService.findById(Integer.parseInt(s));
    }

    @Override
    public String print(SizeRepr sizeRepr, Locale locale) {
        return Integer.toString(sizeRepr.getId());
    }
}
