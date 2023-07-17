package sn.guru.springframework.reactivemongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import sn.guru.springframework.reactivemongo.domain.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
