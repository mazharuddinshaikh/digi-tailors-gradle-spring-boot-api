package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.DressRepository;
import in.tailor.digi.model.Dress;
import in.tailor.digi.utils.DtsCodeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Setter
@Slf4j
public non-sealed class DressServiceImpl implements DressService {
    @Autowired
    private DressRepository dressRepository;

    @Override
    public List<Dress> getDressByUser(String userId, int offset, int limit) {
        return dressRepository.getDressByUser(userId, offset, limit);
    }

    @Override
    public List<Dress> getDressByShop(String shopId, int offset, int limit) {
        return dressRepository.getDressByShop(shopId, offset, limit);
    }

    @Override
    public List<Dress> getDressByCustomer(String customerId, int offset, int limit) {
        return dressRepository.getDressByCustomer(customerId, offset, limit);
    }

    @Override
    public Optional<Dress> getDressByDressId(String dressId) {
        return dressRepository.getDressByDressId(dressId);
    }

    @Override
    @Transactional
    public Optional<Dress> addDress(Dress dress) {
        dress.setDressId(DtsCodeUtils.generateDressId());
        int dressAdded = dressRepository.addDress(dress);
        if (dressAdded > 0) {
            return Optional.of(dress);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Dress> updateDress(Dress dress) {
        int dressAdded = dressRepository.updateDress(dress);
        if (dressAdded > 0) {
            return Optional.of(dress);
        }
        return Optional.empty();
    }
}
