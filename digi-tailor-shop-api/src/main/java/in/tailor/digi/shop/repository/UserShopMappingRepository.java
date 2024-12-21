package in.tailor.digi.shop.repository;

import in.tailor.digi.model.UserShopMapping;

public sealed interface UserShopMappingRepository permits UserShopMappingRepositoryImpl {

	int addUserShopMapping(UserShopMapping userShopMapping);

	int updateUserShopMapping(UserShopMapping userShopMapping);

	int deleteUserShopMapping(UserShopMapping userShopMapping);
}