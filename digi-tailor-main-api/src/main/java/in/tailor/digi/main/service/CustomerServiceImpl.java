package in.tailor.digi.main.service;

import in.tailor.digi.main.repository.CustomerRepository;
import in.tailor.digi.model.Customer;
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
public non-sealed class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomerByUser(String userId, int offset, int limit) {
        return customerRepository.getCustomerByUser(userId, offset, limit);
    }

    @Override
    public Optional<Customer> getCustomerByCustomerId(String customerId) {
        return customerRepository.getCustomerByCustomerId(customerId);
    }

    @Override
    public List<Customer> getCustomerByShop(String shopId, int offset, int limit) {
        return customerRepository.getCustomerByShop(shopId, offset, limit);
    }

    @Override
    @Transactional
    public Optional<Customer> addCustomer(Customer customer) {
        customer.setCustomerId(DtsCodeUtils.generateCustomerId());
        int customerAdded = customerRepository.addCustomer(customer);
        return customerAdded > 0 ? Optional.of(customer) : Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Customer> updateCustomer(Customer customer) {
        int customerAdded = customerRepository.updateCustomer(customer);
        return customerAdded > 0 ? Optional.of(customer) : Optional.empty();
    }
}
