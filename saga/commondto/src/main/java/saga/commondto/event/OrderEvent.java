package saga.commondto.event;

import java.util.Date;
import java.util.UUID;

import saga.commondto.dtoclasses.OrderRequest;

public class OrderEvent implements Event {
	private UUID evendId = UUID.randomUUID();
	private Date eventDate = new Date();
	private OrderRequest orderRequestDto;
	private OrderStatus orderStatus;

	public OrderEvent(OrderRequest orderRequestDto, OrderStatus orderStatus) {

		this.orderRequestDto = orderRequestDto;
		this.orderStatus = orderStatus;

	}

	@Override
	public UUID getEventId() {

		return evendId;
	}

	@Override
	public Date getEventDate() {

		return eventDate;
	}

	public OrderRequest getOrderRequestDto() {

		return orderRequestDto;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

}
