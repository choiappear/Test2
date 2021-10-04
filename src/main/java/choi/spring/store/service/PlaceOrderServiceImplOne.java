package choi.spring.store.service;

import choi.spring.store.dao.ItemDao;
import choi.spring.store.dao.PaymentInfoDao;
import choi.spring.store.dao.PurchaseOrderDao;
import choi.spring.store.vo.Item;
import choi.spring.store.vo.ItemNotFoundException;
import choi.spring.store.vo.PaymentInfo;
import choi.spring.store.vo.PurchaseOrder;
import choi.spring.store.vo.PurchaseOrderRequest;
import choi.spring.store.vo.PurchaseOrderResult;

public class PlaceOrderServiceImplOne implements PlaceOrderService {
	private ItemDao itemDao;
	private PaymentInfoDao paymentInfoDao;
	private PurchaseOrderDao purchaseOrderDao;
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setPaymentInfoDao(PaymentInfoDao paymentInfoDao) {
		this.paymentInfoDao = paymentInfoDao;
	}

	public void setPurchaseOrderDao(PurchaseOrderDao purchaseOrderDao) {
		this.purchaseOrderDao = purchaseOrderDao;
	}

	@Override
	public PurchaseOrderResult order(PurchaseOrderRequest orderRequest) throws ItemNotFoundException {
		Item item=itemDao.findById(orderRequest.getItemId());
		if(item==null)
			throw new ItemNotFoundException(orderRequest.getItemId());
		PaymentInfo paymentInfo = new PaymentInfo(item.getPrice());
		paymentInfoDao.insert(paymentInfo);
		PurchaseOrder order = new PurchaseOrder(item.getId(),
				orderRequest.getAddress(), paymentInfo.getId());
		purchaseOrderDao.insert(order);
		return new PurchaseOrderResult(item,paymentInfo,order);
	}

}
