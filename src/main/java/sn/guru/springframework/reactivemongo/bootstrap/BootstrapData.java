package sn.guru.springframework.reactivemongo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.guru.springframework.reactivemongo.domain.Beer;
import sn.guru.springframework.reactivemongo.domain.Customer;
import sn.guru.springframework.reactivemongo.repository.BeerRepository;
import sn.guru.springframework.reactivemongo.repository.CustomerRepository;

import java.math.BigDecimal;


@Component
public class BootstrapData implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    public BootstrapData(BeerRepository beerRepository, CustomerRepository customerRepository) {
        this.beerRepository = beerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
        System.out.println("Loaded Beers: " + beerRepository.count().block());
        loadCustomerObjects();
        System.out.println("Loaded Customers: " + customerRepository.count().block());

    }
    void loadBeerObjects() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                //load data
                Beer beer1 = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("Pale Ale")
                        .quantityOnHand(200)
                        .upc("337010000001L")
                        .price(new BigDecimal("12.95"))
                        .build();
                Beer beer2 = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("Pale Ale")
                        .quantityOnHand(200)
                        .upc("337010000002L")
                        .price(new BigDecimal("12.95"))
                        .build();
                Beer beer3 = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("Pale Ale")
                        .quantityOnHand(200)
                        .upc("337010000003L")
                        .price(new BigDecimal("12.95"))
                        .build();
                Beer beer4 = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("Pale Ale")
                        .quantityOnHand(200)
                        .upc("337010000004L")
                        .price(new BigDecimal("12.95"))
                        .build();
                Beer beer5 = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("Pale Ale")
                        .quantityOnHand(200)
                        .upc("337010000005L")
                        .price(new BigDecimal("12.95"))
                        .build();
                Beer beer6 = Beer.builder()
                        .beerName("Guru")
                        .beerStyle("Pale Ale")
                        .quantityOnHand(200)
                        .upc("337010000006L")
                        .price(new BigDecimal("12.95"))
                        .build();
                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
                beerRepository.save(beer4).subscribe();
                beerRepository.save(beer5).subscribe();
                beerRepository.save(beer6).subscribe();
            }

        });
    }
    public void loadCustomerObjects() {
        customerRepository.count().subscribe(count -> {
            if (count == 0) {
                Customer customer1 = Customer.builder()
                        .customerName("Guru")
                        .build();
                Customer customer2 = Customer.builder()
                        .customerName("Sam")
                        .build();
                Customer customer3 = Customer.builder()
                        .customerName("John")
                        .build();
                Customer customer4 = Customer.builder()
                        .customerName("Doe")
                        .build();
                Customer customer5 = Customer.builder()
                        .customerName("Jane")
                        .build();
                Customer customer6 = Customer.builder()
                        .customerName("Jack")
                        .build();
                customerRepository.save(customer1).subscribe();
                customerRepository.save(customer2).subscribe();
                customerRepository.save(customer3).subscribe();
                customerRepository.save(customer4).subscribe();
                customerRepository.save(customer5).subscribe();
                customerRepository.save(customer6).subscribe();
            }
        });
    }
}
