package epos.checkout;

public class CheckoutOutput {

    private String item;

    private Long price;

    private Long quantity;

    private Long discount = 0L;

    private Long total;

    public String getItem() {
        return item;
    }

    public CheckoutOutput setItem(String item) {
        this.item = item;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public CheckoutOutput setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public CheckoutOutput setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Long getDiscount() {
        return discount;
    }

    public CheckoutOutput setDiscount(Long discount) {
        this.discount = discount;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public CheckoutOutput setTotal(Long total) {
        this.total = total;
        return this;
    }

}
