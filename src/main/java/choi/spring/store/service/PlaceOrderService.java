package choi.spring.store.service;

import choi.spring.store.vo.ItemNotFoundException;
import choi.spring.store.vo.PurchaseOrderRequest;
import choi.spring.store.vo.PurchaseOrderResult;

public interface PlaceOrderService {
	public PurchaseOrderResult order(PurchaseOrderRequest orderRequest)
			throws ItemNotFoundException;
}
