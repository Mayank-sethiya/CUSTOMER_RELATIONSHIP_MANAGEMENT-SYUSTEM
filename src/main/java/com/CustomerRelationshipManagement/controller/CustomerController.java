package com.CustomerRelationshipManagement.controller;

import com.CustomerRelationshipManagement.entity.CustomerEntity;
import com.CustomerRelationshipManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // POST /api/customers: Create a new customer
    @PostMapping
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        return customerService.saveCustomer(customer);
    }

    // GET /api/customers: Get all customers
    @GetMapping
    public Iterable<CustomerEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // GET /api/customers/{id}: Get customer by ID
    @GetMapping("/{id}")
    public CustomerEntity getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // PUT /api/customers/{id}: Update customer details
    @PutMapping("/{id}")
    public CustomerEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    // DELETE /api/customers/{id}: Delete customer by ID
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
