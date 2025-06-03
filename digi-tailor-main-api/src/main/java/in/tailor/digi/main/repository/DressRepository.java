package in.tailor.digi.main.repository;

import in.tailor.digi.model.Dress;

import java.util.List;
import java.util.Optional;

public sealed interface DressRepository permits DressRepositoryImpl {
    List<Dress> getDressByUser(String userId, int offset, int limit);

    List<Dress> getDressByShop(String userId, int offset, int limit);

    List<Dress> getDressByCustomer(String userId, int offset, int limit);

    Optional<Dress> getDressByDressId(String dressId);

    int addDress(Dress dress);

    int updateDress(Dress dress);
}
