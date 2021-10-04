package choi.spring.store.dao;

import choi.spring.store.vo.Item;

public interface ItemDao {
	public Item findById(Integer itemId);
}
