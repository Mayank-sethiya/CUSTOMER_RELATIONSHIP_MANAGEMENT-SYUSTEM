package com.CustomerRelationshipManagement.service;

import com.CustomerRelationshipManagement.entity.CustomerEntity;
import com.CustomerRelationshipManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new customer
    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public Iterable<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by ID
    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Update a customer's details
    public CustomerEntity updateCustomer(Long id, CustomerEntity customerDetails) {
        CustomerEntity existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setEmail(customerDetails.getEmail());
            existingCustomer.setPhone(customerDetails.getPhone());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
