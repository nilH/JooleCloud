package com.itlize.Joole.integrationTest;

        import com.fasterxml.jackson.core.type.TypeReference;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.fasterxml.jackson.databind.json.JsonMapper;
        import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
        import com.itlize.Joole.entity.Product;
        import com.itlize.Joole.entity.Project;
        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.MvcResult;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

        import java.nio.charset.StandardCharsets;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.StandardOpenOption;
        import java.util.List;

        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class AddProductToProjectTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    public void login() throws Exception{

        String username = "username1";
        String password = "password";
        ObjectMapper objectMapper= new ObjectMapper();
        MvcResult mvcResult=mockMvc.perform(get("/user/login").param("username",username).param("password",password))
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=ISO-8859-1"))).andReturn();
        String token=mvcResult.getResponse().getContentAsString();
        Files.writeString(Path.of("src/test/resources/jwt"),token,
                StandardCharsets.ISO_8859_1, StandardOpenOption.WRITE);
    }
    @Test
    public void addProject() throws Exception{
        login();

        Project project=new Project();
        project.setProjectName("projectname!!!");

        ObjectMapper objectMapper= new ObjectMapper();
        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);

        mockMvc.perform(post("/project/add_project").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(project))
        );
    }

    @Test
    public void addProduct() throws Exception{
        login();

        Product product=new Product();
        product.setProductName("productnamefortest!!!");
        product.setBrand("brand");
        product.setType("searchType");
        product.setCertificate("certificate");
        product.setModelYear(2015);
        product.setAirflow(1);
        product.setApplication("application---");
        product.setHeight(1);
        product.setMaxPower(1);
        product.setMountingLocation("location");
        product.setManufacturer("manufacturer");
        product.setUserType("usertype");
        product.setFanSweepDiameter(1);
        product.setCertificate("certificate");
        product.setAccessories("accessories");
        product.setType("nameforsearch");

        ObjectMapper objectMapper= new ObjectMapper();
        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);

        mockMvc.perform(post("/product/add_product").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(product))
        );
    }

    @Test
    public void Search() throws Exception{
        login();
        List<Product> productList;
        ObjectMapper objectMapper= JsonMapper.builder().addModule(new JavaTimeModule()).build();
        MvcResult mvcResult=mockMvc.perform(get("/product/search_by_type").param("type","fan"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        productList=objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<Product>>() {
        });
        System.out.println(productList.get(0).getProductId());
    }

    @Test
    public void addProductToProject() throws Exception{
        login();

        String productId = "2";
        String projectId = "2";

        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);
        MvcResult mvcResult=mockMvc.perform(post("/project/add_product_to_project")
                .header("Authorization","Bearer "+token).param("project_id", projectId).param("product_id",productId)).andReturn();
        String type=mvcResult.getResponse().getContentType();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println(type+response);
    }

    @Test
    public void getProjectFromProduct() throws Exception{
        login();

        String productId = "2";

        ObjectMapper objectMapper= new ObjectMapper();
        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);

        MvcResult mvcResult=mockMvc.perform(get("/product/get_project_from_product")
                        .header("Authorization","Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("product_id",productId))
                .andReturn();
        String type=mvcResult.getResponse().getContentType();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println(type+response);


    }

    @Test
    public void getProductFromProject() throws Exception{
        login();

        String projectId = "2";

        ObjectMapper objectMapper= new ObjectMapper();
        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);

        MvcResult mvcResult=mockMvc.perform(get("/project/get_product_from_project")
                        .header("Authorization","Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("project_id",projectId))
                .andReturn();
        String type=mvcResult.getResponse().getContentType();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println(type+response);
        //System.out.println(mvcResult);

    }

    @Test
    public void deleteProductFromProject() throws Exception{

        login();

        String productId = "2";
        String projectId = "2";

        String token=Files.readString(Path.of("src/test/resources/jwt"), StandardCharsets.ISO_8859_1);
        MvcResult mvcResult=mockMvc.perform(post("/project/delete_product_from_project")
                .header("Authorization","Bearer "+token).param("project_id", projectId).param("product_id",productId)).andReturn();
        String type=mvcResult.getResponse().getContentType();
        String response=mvcResult.getResponse().getContentAsString();
        System.out.println(type+response);
    }

}
