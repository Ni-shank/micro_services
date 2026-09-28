package tp.com.dto;

public class OrderWithUserDto {
    private Long id;
    private String product;
    private Double amount;
    private UserDto user;

    public OrderWithUserDto() {}

    public OrderWithUserDto(Long id, String product, Double amount, UserDto user) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }
}
