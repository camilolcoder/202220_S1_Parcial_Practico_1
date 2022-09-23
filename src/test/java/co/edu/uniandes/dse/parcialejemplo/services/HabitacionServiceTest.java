package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HabitacionService.class)
@Slf4j

public class HabitacionServiceTest {
    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private TestEntityManager testEntityManager;

    private final PodamFactory factory = new PodamFactoryImpl();
    private final List<HabitacionEntity> habitaciones = new ArrayList<>();

    @BeforeEach
    void setUp(){
        clearData();
        insertData();
    }

    private void clearData(){
        testEntityManager.getEntityManager().createQuery("delete from HabitacionEntity");
    }

    private void insertData(){
        for (int i = 0; i < 3; i++){
            HabitacionEntity habitacionEntity = factory.manufacturePojo(HabitacionEntity.class);
            testEntityManager.persist(habitacionEntity);
            habitaciones.add(habitacionEntity);
        }
    }

    public void testCreateHabitacion() throws IllegalOperationException {
        HabitacionEntity habitacion = habitacionService.createHabitacion(factory.manufacturePojo(HabitacionEntity.class));

        assertNotNull(habitacion);

        HabitacionEntity foundHabitacion = testEntityManager.find(HabitacionEntity.class, habitacion.getId());

        assertEquals(foundHabitacion.getCantidadBanos(), habitacion.getCantidadBanos());
        assertEquals(foundHabitacion.getHotel(), habitacion.getHotel());
        assertEquals(foundHabitacion.getIdentificacion(), habitacion.getIdentificacion());
        assertEquals(foundHabitacion.getId(), habitacion.getId());
        assertEquals(foundHabitacion.getCantidadCamas(), habitacion.getCantidadCamas());

        HabitacionEntity throwableHabitacion = factory.manufacturePojo(HabitacionEntity.class);

        throwableHabitacion.setHotel(null);

        IllegalOperationException nullNameException = assertThrows(IllegalOperationException.class,
                () -> habitacionService.createHabitacion(throwableHabitacion));

    }
}
