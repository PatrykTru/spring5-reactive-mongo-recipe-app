package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
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
public class UnitOfMeasureReactiveRepositoryIT {
    @Autowired
    UnitOfMeasureReactiveRepository uomReactiveRepository;

    @Before
    public void setUp() throws Exception {
        uomReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Pint");

        uomReactiveRepository.save(unitOfMeasure).block();

        assertNotNull(uomReactiveRepository.findByDescription("Pint"));

    }

    @Test
    public void findByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("Pint");

        uomReactiveRepository.save(unitOfMeasure).then().block();

        UnitOfMeasure responseUom = uomReactiveRepository.findByDescription("Pint").block();

        assertEquals(responseUom.getDescription(), "Pint");

    }

}