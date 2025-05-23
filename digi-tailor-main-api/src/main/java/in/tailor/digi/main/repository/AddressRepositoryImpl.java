package in.tailor.digi.main.repository;

import in.tailor.digi.model.Address;
import in.tailor.digi.model.Shop;
import in.tailor.digi.utils.DtsDateTimeUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Setter
public non-sealed class AddressRepositoryImpl implements AddressRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int updateShopAddress(Shop shop) {
		return jdbcTemplate.update("UPDATE DTS_SHOP SET ADDRESS_ID= ?, UPDATED_AT = ? WHERE SHOP_ID = ?",
				shop.getShopAddress().getAddressId(), DtsDateTimeUtil.getIndianCurrentDateTime(), shop.getShopId());
	}

	@Override
	public int addAddress(Shop shop) {
		return jdbcTemplate.update(
				"INSERT INTO DTS_ADDRESS (ADDRESS_ID, ADD_LINE_ONE, ADD_LINE_TWO, CITY, STATE, ZIPCODE, CREATED_AT, UPDATED_AT, UPDATED_BY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				shop.getShopAddress().getAddressId(), shop.getShopAddress().getAddressLineOne(),
				shop.getShopAddress().getAddressLineTwo(), shop.getShopAddress().getCity(),
				shop.getShopAddress().getState(), shop.getShopAddress().getZipCode(),
				DtsDateTimeUtil.getIndianCurrentDateTime(), null, shop.getShopOwner().getUserId());
	}

	@Override
	public int updateAddress(Address address) {
		return jdbcTemplate.update(
				"UPDATE DTS_ADDRESS SET ADD_LINE_ONE = ?, ADD_LINE_TWO = ?, CITY = ?, STATE = ?, ZIPCODE = ?, UPDATED_AT = ? WHERE ADDRESS_ID = ?",
				address.getAddressLineOne(), address.getAddressLineTwo(), address.getCity(), address.getState(),
				address.getZipCode(), DtsDateTimeUtil.getIndianCurrentDateTime(), address.getAddressId());
	}

}
