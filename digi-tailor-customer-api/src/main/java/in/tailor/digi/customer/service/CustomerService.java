package in.tailor.digi.customer.service;

import in.tailor.digi.model.Customer;

import java.util.List;
import java.util.Optional;

public sealed interface CustomerService permits CustomerServiceImpl {
    List<Customer> getCustomerByUser(String userId, int limit, int offset);

    List<Customer> getCustomerByShop(String shopId, int limit, int offset);

    Optional<Customer> addCustomer(Customer customer);

    Optional<Customer> updateCustomer(Customer customer);
}
