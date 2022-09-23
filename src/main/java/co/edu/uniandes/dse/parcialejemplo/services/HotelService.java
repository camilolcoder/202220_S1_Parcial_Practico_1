package co.edu.uniandes.dse.parcialejemplo.services;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HotelService {

    @NonNull
    private HotelRepository hotelRepository;

    @Transactional
    public HotelEntity createHoteles(HotelEntity hotelEntity) throws IllegalOperationException {



        if (!hotelRepository.findByNombre(hotelEntity.getNombre()).isEmpty()){
            throw new IllegalOperationException("Un hotel ya tienen ese nombre");
        }

        if (hotelEntity.getNumeroEstrellas() <= 1 || hotelEntity.getNumeroEstrellas() >= 5){
            throw new IllegalOperationException("Solo hay estrellas entre 1 y 5");
        }

        return hotelRepository.save(hotelEntity);
    }
}
