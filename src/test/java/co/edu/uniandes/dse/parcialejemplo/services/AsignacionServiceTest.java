package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import({HotelService.class,HabitacionService.class})
@Slf4j

public class AsignacionServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager testEntityManager;

    private final PodamFactory factory = new PodamFactoryImpl();
    private final List<HotelEntity> hoteles = new ArrayList<>();

    private final List<HabitacionEntity> habitaciones = new ArrayList<>();

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
}
