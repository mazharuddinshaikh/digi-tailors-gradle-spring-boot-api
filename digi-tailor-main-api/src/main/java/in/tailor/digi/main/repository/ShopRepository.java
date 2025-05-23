package in.tailor.digi.main.repository;

import in.tailor.digi.model.Shop;

import java.util.List;
import java.util.Optional;

public sealed interface ShopRepository permits ShopRepositoryImpl {

	Optional<Shop> getShopByShopId(String shopId);

	List<Shop> getShopList(int offset, int limit);

	List<Shop> getShopListByUser(String userId, int offset, int limit);

	int addShop(Shop shop);

	int updateShop(Shop shop);

	int deleteShopByShopId(int shopId);

}