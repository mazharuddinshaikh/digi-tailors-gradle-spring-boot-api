/**
 *
 */
package in.tailor.digi.shop.service;

import in.tailor.digi.model.Shop;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public sealed interface ShopService permits ShopServiceImpl {
    Optional<Shop> getShopByShopId(String shopId);

    List<Shop> getAllShopList(int offset, int limit);

    List<Shop> getShopListByUser(String userId, int offset, int limit);

    Optional<Shop> addShop(Shop shop);

    Optional<Shop> updateShop(Shop shop);

    Optional<Shop> updateShopAddress(Shop shop);

    boolean deleteShopByShopId(int shopId);
}
