package choi.spring.store.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import choi.spring.store.dao.ItemDao;
import choi.spring.store.vo.Item;

public class JdbcItemDao implements ItemDao {
	private JdbcTemplate jdbcTemplate;
	public JdbcItemDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public Item findById(Integer itemId) {
		return jdbcTemplate.queryForObject(
				"select * from item where item_id=?",
				new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						Item item = new Item();
						item.setId(rs.getInt("item_id"));
						item.setPrice(rs.getInt("price"));
						return item;
					}
					
				},new Object[] {itemId});
				
	}

}
