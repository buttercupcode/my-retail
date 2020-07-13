package com.target.myretail;

import com.target.myretail.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindById_thenReturnProduct() {
        // given
        Product p1 = new Product();
        p1.setId(1);
        entityManager.persist(p1);
        entityManager.flush();

        // when
        Product found = productRepository.findById(p1.getId()).get();

        // then
        assertEquals(found.getId(), p1.getId());
    }

    @Test
    public void whenUpdate_thenReturnUpdatedValue() {
        // given
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("junk product");
        entityManager.persist(p1);
        entityManager.flush();

        // when
        Product found = productRepository.findById(p1.getId()).get();
        found.setPrice(12.55);
        entityManager.persist(found);
        entityManager.flush();

        // when
        Product found2 = productRepository.findById(p1.getId()).get();
        // then
        assertEquals(found2.getPrice(), found.getPrice());
    }

}