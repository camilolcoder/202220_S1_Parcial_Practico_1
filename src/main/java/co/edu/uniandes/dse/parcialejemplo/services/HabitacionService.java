package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HabitacionEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HabitacionService {

    @NonNull
    private HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity createHabitacion(HabitacionEntity habitacionEntity) throws IllegalOperationException {

        if (habitacionEntity.getHotel() == null){
            throw new IllegalOperationException("Se necesita estar asociado a un hotel");
        }

        if (habitacionEntity.getIdentificacion() > habitacionEntity.getCantidadCamas()){
            throw new IllegalOperationException("Una habitación solo puede tener identificación <= camas");
        }

        return habitacionRepository.save(habitacionEntity);
    }
}
