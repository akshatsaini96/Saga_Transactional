package saga.commondto.dtoclasses;

import lombok.Data;

@Data
public class OrderRequest {

	private int userId;
	private int productId;
	private int amount;
	private int orderId;

}
