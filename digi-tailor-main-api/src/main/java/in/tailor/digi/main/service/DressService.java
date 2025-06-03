package in.tailor.digi.main.service;

import in.tailor.digi.model.Dress;

import java.util.List;
import java.util.Optional;

public sealed interface DressService permits DressServiceImpl {
    List<Dress> getDressByUser(String userId, int offset, int limit);

    List<Dress> getDressByShop(String shopId, int offset, int limit);

    List<Dress> getDressByCustomer(String customerId, int offset, int limit);

    Optional<Dress> getDressByDressId(String dressId);

    Optional<Dress> addDress(Dress dress);

    Optional<Dress> updateDress(Dress dress);

}
