package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignacionService {

    @NonNull
    private HabitacionRepository habitacionRepository;

    @NonNull
    private HotelRepository hotelRepository;

    @Transactional
    public void addHabitacion(Long idHabitacion, Long idHotel) throws IllegalOperationException {

        if (hotelRepository.findById(idHotel).isEmpty()){
            throw new IllegalOperationException("El hotel no existe");
        }
        if (habitacionRepository.findById(idHabitacion).isEmpty()){
            throw new IllegalOperationException("La habitación no existe");
        }

        hotelRepository.findById(idHotel).get().getHabitaciones().add(habitacionRepository.getById(idHabitacion));
    }
}
