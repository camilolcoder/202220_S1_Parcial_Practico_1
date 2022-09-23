package co.edu.uniandes.dse.parcialejemplo.entities;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

public class HotelEntity extends BaseEntity{
    private String nombre;
    private String direccion;
    private Integer numeroEstrellas;

    @OneToMany
    private List<HabitacionEntity> habitaciones = new ArrayList<>();
}
