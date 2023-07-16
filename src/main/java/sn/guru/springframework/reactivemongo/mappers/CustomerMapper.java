package sn.guru.springframework.reactivemongo.mappers;


import org.mapstruct.Mapper;
import sn.guru.springframework.reactivemongo.domain.Customer;
import sn.guru.springframework.reactivemongo.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customer);
    CustomerDTO customerToCustomerDto(Customer customer);
}
