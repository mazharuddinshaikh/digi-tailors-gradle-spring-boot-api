package in.tailor.digi.main.service;

import in.tailor.digi.model.Dress;

import java.util.List;
import java.util.Optional;

public sealed interface DressService permits DressServiceImpl {
    List<Dress> getDressByUser(String userId, int limit, int offset);

    List<Dress> getDressByShop(String shopId, int limit, int offset);

    List<Dress> getDressByCustomer(String customerId, int limit, int offset);

    Optional<Dress> getDressByDressId(String dressId);

    Optional<Dress> addDress(Dress dress);

    Optional<Dress> updateDress(Dress dress);

}
