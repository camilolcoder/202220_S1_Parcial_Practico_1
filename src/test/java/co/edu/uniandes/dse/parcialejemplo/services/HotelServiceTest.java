package co.edu.uniandes.dse.parcialejemplo.services;


import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelService.class)
@Slf4j


public class HotelServiceTest {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private TestEntityManager testEntityManager;

    private final PodamFactory factory = new PodamFactoryImpl();
    private final List<HotelEntity> hoteles = new ArrayList<>();

    @BeforeEach
    void setUp(){
        clearData();
        insertData();
    }

    private void clearData(){
        testEntityManager.getEntityManager().createQuery("delete from HotelEntity");
    }

    private void insertData(){
        for (int i = 0; i < 3; i++){
            HotelEntity hotelEntity = factory.manufacturePojo(HotelEntity.class);
            testEntityManager.persist(hotelEntity);
            hoteles.add(hotelEntity);
        }
    }

    public void testCreateHotel() throws IllegalOperationException {
        HotelEntity hotel = hotelService.createHoteles(factory.manufacturePojo(HotelEntity.class));

        assertNotNull(hotel);

        HotelEntity foundHotel = testEntityManager.find(HotelEntity.class, hotel.getId());

        assertEquals(foundHotel.getDireccion(), hotel.getDireccion());
        assertEquals(foundHotel.getHabitaciones(), hotel.getHabitaciones());
        assertEquals(foundHotel.getNombre(), hotel.getNombre());
        assertEquals(foundHotel.getId(), hotel.getId());

    }
}
