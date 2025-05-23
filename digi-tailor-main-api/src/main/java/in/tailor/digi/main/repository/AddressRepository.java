package in.tailor.digi.main.repository;

import in.tailor.digi.model.Address;
import in.tailor.digi.model.Shop;

public sealed interface AddressRepository permits AddressRepositoryImpl {
	int updateShopAddress(Shop shop);

	int updateAddress(Address address);

	int addAddress(Shop shop);

}
