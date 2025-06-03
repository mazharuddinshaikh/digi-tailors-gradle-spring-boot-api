package in.tailor.digi.main.repository;

import in.tailor.digi.model.Customer;

import java.util.List;
import java.util.Optional;

public sealed interface CustomerRepository permits CustomerRepositoryImpl {
    List<Customer> getCustomerByUser(String userId, int offset, int limit);

    Optional<Customer> getCustomerByCustomerId(String customerId);

    List<Customer> getCustomerByShop(String shopId, int offset, int limit);

    int addCustomer(Customer customer);

    int updateCustomer(Customer customer);
}
