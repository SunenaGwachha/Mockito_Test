package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.domain.Vendor;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
class MockServicesRepoTest {

   // THIS       IS   FOR TESTING REPOSITORY / SERVICE THROUGH @MOCKBEAN

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;


    @Test
    public void getProductTest() {
        List<Product> productList = new ArrayList<>();
        Vendor vendor = new Vendor(1L, "XYZ", "NEPAL KTM", "2092092090");
        productList.add(new Product(1L, "Head Phone1", "Good and Nice1", LocalDate.of(2020, 9, 9), new BigDecimal(34.45), 23, vendor));
        productList.add(new Product(2L, "Head Phone2", "Good and Nice2", LocalDate.of(2020, 9, 9), new BigDecimal(34.45), 23, vendor));
        productList.add(new Product(3L, "Head Phone3", "Good and Nice3", LocalDate.of(2020, 9, 9), new BigDecimal(34.45), 23, vendor));
        when(repository.findAll()).thenReturn(productList);
        assertEquals(3, service.getAll().size());
    }

    @Test
    public void saveProductTest() {

        Vendor vendor = new Vendor(1L, "XYZ", "NEPAL KTM", "2092092090");
        Product p=new Product(4L, "Head Phone4", "Good and Nice1", LocalDate.of(2020, 9, 9), new BigDecimal(34.45), 23, vendor);

        when(repository.save(p)).thenReturn(p);

        assertEquals(p, service.save(p));
    }


    @Test
    public void deleteProductTest() {



        service.del(5L);

        verify(repository,times(1)).deleteById(5L);


    }



}
