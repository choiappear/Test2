package choi.spring.store.dao.jdbc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import choi.spring.store.dao.PaymentInfoDao;
import choi.spring.store.vo.PaymentInfo;

public class JdbcPaymentInfoDao implements PaymentInfoDao {
	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	public JdbcPaymentInfoDao(DataSource dataSource) {
		this.insert = new SimpleJdbcInsert(dataSource);
		insert.withTableName("payment_info").usingColumns(
				"payment_info_id","price");
	}
	public void setNamedJdbcTemplate(
			DataSource dataSource) {
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	public int nextVal() {
		return namedJdbcTemplate.queryForObject(
				"select payment_seq.nextval from dual",
				Collections.emptyMap(),Integer.class);
	}
	@Override
	public void insert(PaymentInfo paymentInfo) {
		Map<String, Object> paramValueMap=new HashMap<String,Object>();
		paymentInfo.setId(nextVal());
		paramValueMap.put("payment_info_id", paymentInfo.getId());
		paramValueMap.put("price", paymentInfo.getPrice());
		insert.execute(paramValueMap);
	}

}
