package choi.spring.store.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import choi.spring.store.service.PlaceOrderService;
import choi.spring.store.vo.PurchaseOrderRequest;
import choi.spring.store.vo.PurchaseOrderResult;

public class OrderServiceTestTwo {
	private PlaceOrderService placeOrderService;
	private AbstractApplicationContext context;

	public OrderServiceTestTwo() {
		String[] configLocations = new String[] { "applicationContext.xml", "transactionTwo.xml" };
		context = new ClassPathXmlApplicationContext(configLocations);
		placeOrderService = (PlaceOrderService) context.getBean("placeOrderService");
	}

	public void order() {
		PurchaseOrderRequest orderRequest = new PurchaseOrderRequest();
		orderRequest.setItemId(3);
		orderRequest.setAddress("서울 망원동");
		PurchaseOrderResult orderResult = placeOrderService.order(orderRequest);
		System.out.println("주문상태 정보");
		System.out.println("아이템 : " + orderResult.getItem().getId());
		System.out.println("가격 : " + orderResult.getPaymentInfo().getPrice());
	}

	public void close() {
		context.close();
	}

	public static void main(String[] args) {
		OrderServiceTestTwo test = new OrderServiceTestTwo();
		test.order();
		test.close();
	}
}
