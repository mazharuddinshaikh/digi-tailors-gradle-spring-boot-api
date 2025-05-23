/**
 *
 */
package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.AddressRepository;
import in.tailor.digi.main.repository.ShopRepository;
import in.tailor.digi.main.repository.UserShopMappingRepository;
import in.tailor.digi.model.Shop;
import in.tailor.digi.model.UserShopMapping;
import in.tailor.digi.utils.DtsCodeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
@Setter
@Slf4j
public non-sealed class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserShopMappingRepository userShopMappingRepository;

    @Override
    public Optional<Shop> getShopByShopId(String shopId) {
        return shopRepository.getShopByShopId(shopId);
    }

    @Override
    public List<Shop> getAllShopList(int offset, int limit) {
        return shopRepository.getShopList(offset, limit);
    }

    @Override
    public List<Shop> getShopListByUser(String userId, int offset, int limit) {
        return shopRepository.getShopListByUser(userId, offset, limit);
    }

    @Override
    @Transactional
    public Optional<Shop> addShop(Shop shop) {
        shop.setShopId(DtsCodeUtils.generateShopId());
        shop.setShopCode(DtsCodeUtils.generateShopCode());
        if (shop.getShopAddress() != null) {
            shop.getShopAddress().setAddressId(DtsCodeUtils.generateAddressId());
            addressRepository.addAddress(shop); // add address
        }
        int shopAdded = shopRepository.addShop(shop); // add shop
        userShopMappingRepository.addUserShopMapping(
                UserShopMapping.builder().userId(shop.getShopOwner().getUserId()).shopId(shop.getShopId()).build());
        if (shopAdded > 0) {
            log.info("shop added successfully. shop id {}", shop.getShopId());
            return Optional.of(shop);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Shop> updateShop(Shop shop) {
        Shop updateShop =  getShopByShopId(shop.getShopId()).get();
        updateShop.setShopName(shop.getShopName());
        int shopUpdated = shopRepository.updateShop(updateShop); // update shop
        if (shopUpdated > 0) {
            log.info("shop updated successfully. shop id {}", updateShop.getShopId());
            return Optional.of(updateShop);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Shop> updateShopAddress(Shop shop) {
        if (shop.getShopAddress().getAddressId() != null) {
            addressRepository.updateAddress(shop.getShopAddress()); // update address in address table
        } else {
            shop.getShopAddress().setAddressId(DtsCodeUtils.generateAddressId());
            addressRepository.addAddress(shop); // add address in address table
            addressRepository.updateShopAddress(shop); // update address id in shop table
        }
        log.info("address updated successfully for shop id {}", shop.getShopId());
        return Optional.of(shop);
    }

    @Override
    @Transactional
    public boolean deleteShopByShopId(int shopId) {
        int shopDeleted = shopRepository.deleteShopByShopId(shopId); // delete shop
        if (shopDeleted > 0) {
            log.info("shop deleted successfully shop id {}", shopId);
            return true;
        }
        return false;
    }

}
