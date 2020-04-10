package nikdevs.onlinestore.service.repr;

public class CartItemRepr {

    private Long productId;
    private Integer qty;
    private SizeRepr size;
    private String pageUrl;

    public CartItemRepr() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public SizeRepr getSize() {
        return size;
    }

    public void setSize(SizeRepr size) {
        this.size = size;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
