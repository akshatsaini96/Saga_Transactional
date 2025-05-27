package saga.commondto.dtoclasses;

import lombok.Data;
import saga.commondto.event.OrderStatus;

@Data
public class OrderResponse {

	private int userId;
	private int productId;
	private int amount;
	private int orderId;

	private OrderStatus orderStatus;

}
