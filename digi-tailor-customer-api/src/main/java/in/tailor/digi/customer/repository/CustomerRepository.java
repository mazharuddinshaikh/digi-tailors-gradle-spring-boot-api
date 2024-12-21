package in.tailor.digi.customer.repository;

import in.tailor.digi.model.Customer;

import java.util.List;

public sealed interface CustomerRepository permits CustomerRepositoryImpl {
    List<Customer> getCustomerByUser(String userId, int limit, int offset);

    List<Customer> getCustomerByShop(String shopId, int limit, int offset);
    int addCustomer(Customer customer);
    int updateCustomer(Customer customer);
}
