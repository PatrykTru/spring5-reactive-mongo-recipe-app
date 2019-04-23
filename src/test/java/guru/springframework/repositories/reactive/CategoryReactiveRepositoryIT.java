package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory(){
        Category category = new Category();
        category.setDescription("Kebab");

        categoryReactiveRepository.save(category).block();

        assertNotNull(categoryReactiveRepository.findByDescription("Kebab"));

    }

    @Test
    public void findByDescription() {
        Category category = new Category();
        category.setDescription("Taste");

        categoryReactiveRepository.save(category).then().block();

        Category responseCat = categoryReactiveRepository.findByDescription("Taste").block();

        assertEquals(responseCat.getDescription() , "Taste");

    }
}