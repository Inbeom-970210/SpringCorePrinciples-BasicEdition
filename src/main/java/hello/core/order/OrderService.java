package hello.core.order;

public interface OrderService {
    Order createOrder(Long memverId, String itemName, int itemPrice);
}
