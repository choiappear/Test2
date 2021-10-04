package choi.spring.store.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import choi.spring.store.service.PlaceOrderService;
import choi.spring.store.vo.PurchaseOrderRequest;
import choi.spring.store.vo.PurchaseOrderResult;

public class OrderServiceTestOne {
	public PlaceOrderService placeOrderService;
	private AbstractApplicationContext context;
	public OrderServiceTestOne() {
		String[] config = new String[] {"TransactionOne.xml"};
		context = new ClassPathXmlApplicationContext(config);
		placeOrderService = (PlaceOrderService) context.getBean("placeOrderService");
	}
	public void order() {
		PurchaseOrderRequest orderRequest = new PurchaseOrderRequest();
		orderRequest.setItemId(1);
		orderRequest.setAddress("서울 종로구");
		PurchaseOrderResult orderResult = placeOrderService.order(orderRequest);
		System.out.println("주문상태 정보");
		System.out.println("아이템 : "+orderResult.getItem().getId());
		System.out.println("가격 : "+orderResult.getItem().getPrice());
	}
	public void close() {
		context.close();
	}
	public static void main(String[] args) {
		OrderServiceTestOne test = new OrderServiceTestOne();
		test.order();
		test.close();
	}
}
