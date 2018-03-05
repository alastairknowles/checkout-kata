package epos.checkout;

public class Checkout {

    private String item;

    private Long unitPrice;

    private Long quantity;

    private Long discount;

    private Long total;

    public String getItem() {
        return item;
    }

    public Checkout setItem(String item) {
        this.item = item;
        return this;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public Checkout setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Checkout setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public Long getDiscount() {
        return discount;
    }

    public Checkout setDiscount(Long discount) {
        this.discount = discount;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public Checkout setTotal(Long total) {
        this.total = total;
        return this;
    }

}
