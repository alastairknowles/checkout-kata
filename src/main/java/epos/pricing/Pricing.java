package epos.pricing;

public class Pricing {

    private String item;

    private Long price;

    private Offer offer;

    public String getItem() {
        return item;
    }

    public Pricing setItem(String item) {
        this.item = item;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Pricing setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Offer getOffer() {
        return offer;
    }

    public Pricing setOffer(Offer offer) {
        this.offer = offer;
        return this;
    }

    public static class Offer {

        private Long multiple;

        private Long price;

        public Long getMultiple() {
            return multiple;
        }

        public Offer setMultiple(Long multiple) {
            this.multiple = multiple;
            return this;
        }

        public Long getPrice() {
            return price;
        }

        public Offer setPrice(Long price) {
            this.price = price;
            return this;
        }

    }

}
