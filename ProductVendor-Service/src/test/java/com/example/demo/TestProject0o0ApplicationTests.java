package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.domain.Vendor;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(SpringRunner.class)
@SpringBootTest
class TestProject0o0ApplicationTests {



    //THIS       IS   FOR TESTING CONTROLLER THROUGH MOCKMVC

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;




        private MockMvc mockMvc;

        ObjectMapper om=new ObjectMapper();

        @Autowired
        private WebApplicationContext context;

        @Before
        private void setUp(){
         //   mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        }

        @Test
        public void addProductTest() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
            Vendor vendor = new Vendor(1L, "XYZ", "NEPAL KTM", "2092092090");
           // vendor.setProducts(new ArrayList<>());
            Product p=new Product(4L, "Head Phone4", "Good and Nice1", LocalDate.of(2020, 9, 9), new BigDecimal(34.45), 23, vendor);

            String jsonString="{\n" +
                    "  \"name\":\"Product D\",\n" +
                    "  \"description\":\"nice\",\n" +
                    "  \"expiryDate\":\"2020-09-07\",\n" +
                    "  \"unitPrice\":\"10\",\n" +
                    "  \"quantity\":50,\n" +
                    "  \"vendor\":{\n" +
                    "    \"id\":\"1\"\n" +
                    "  }\n" +
                    "\n" +
                    "\n" +
                    "}";

        //    jsonString=om.writeValueAsString(p);


            mockMvc.perform(post("/addproduct").content(jsonString)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                   // .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.name", Matchers.is("Product D")))
                    .andExpect(jsonPath("$.description", Matchers.is("nice")))
                    .andExpect(jsonPath("$.*", Matchers.hasSize(7)));
        }

//Testing is done for controller only here. it is hitting database
     //   @Test
        public void getProductByIdTest() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
            mockMvc.perform(get("/getbyid/1")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.expiryDate", Matchers.is(LocalDate.of(2020,9,7).toString())))
                    .andExpect(jsonPath("$.unitPrice", Matchers.is(120.00)))
                    .andExpect(jsonPath("$.*", Matchers.hasSize(7)));
        }




    //Testing is done for controller without hitting db. we are mocking repository

    @Test
    public void getProductByIdTestWithNoDb() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Vendor vendor = new Vendor(1L, "XYZ", "NEPAL KTM", "2092092090");
        Product p=new Product(4L, "Head Phone4", "Good and Nice1", LocalDate.of(2020, 9, 9), new BigDecimal(34.45), 23, vendor);


        Optional<Product> pOpt=Optional.of(p);



        when(repository.findById(4L)).thenReturn(pOpt);


        mockMvc.perform(get("/getbyid/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.expiryDate", Matchers.is(LocalDate.of(2020,9,9).toString())))
                .andExpect(jsonPath("$.unitPrice", Matchers.is(new BigDecimal(34.45))))
                .andExpect(jsonPath("$.*", Matchers.hasSize(7)));
    }














    /*

    THIS       IS   FOR TESTING REPOSITORY / SERVICE THROUGH @MOCKBEAN

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
    */


}
