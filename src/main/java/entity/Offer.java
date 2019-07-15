package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "items_list")
    private String listOfProducts;
    @Column(name = "sum")
    private int sum;
    @Column(name = "userId")
    private int userId;
    @Column(name = "status")
    private String status;
    @Column(name = "code")
    private String code;

    public Offer() {
        this.id = UUID.randomUUID().toString();
    }

    public Offer(String id, String listOfProducts, int sum, Code code) {
        this.id = id;
        this.listOfProducts = listOfProducts;
        this.sum = sum;
        this.userId = code.getUser().getId();
        this.code = code.getCode();
        this.status = "WORK";
    }


    public Offer(String id, String listOfProducts, int sum, int userId, String status, String code) {
        this.id = id;
        this.listOfProducts = listOfProducts;
        this.sum = sum;
        this.userId = userId;
        this.status = status;
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(String listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
