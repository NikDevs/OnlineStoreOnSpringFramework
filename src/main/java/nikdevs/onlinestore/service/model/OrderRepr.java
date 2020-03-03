package nikdevs.onlinestore.service.model;


import nikdevs.onlinestore.persist.model.Order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepr implements Serializable {

    private Long id;
    private SystemUser user;
    private List<OrderedProductRepr> products;
    private Date date;
    private String country;
    private String zipcode;
    private String adress;
    private String additionalInfo;
    private Integer productCount;
    private BigDecimal total;

    public OrderRepr() {
    }

    public OrderRepr(Order order) {
        this.id = order.getId();
        this.user = new SystemUser(order.getUser());
        this.products = order.getProducts().stream().map(orderedProduct -> new OrderedProductRepr(orderedProduct, this)).collect(Collectors.toList());
        this.date = order.getDate();
        this.productCount = products.size();
        this.total = products.stream().map(value -> value.getPrice().multiply(new BigDecimal(value.getQnt())))
                .reduce(BigDecimal::add).orElse(new BigDecimal(0));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public List<OrderedProductRepr> getProducts() {
        return products;
    }

    public void setProducts(List<OrderedProductRepr> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
