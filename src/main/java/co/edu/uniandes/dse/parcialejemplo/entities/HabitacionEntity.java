package co.edu.uniandes.dse.parcialejemplo.entities;

import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class HabitacionEntity extends BaseEntity{

    private Integer identificacion;
    private Integer cantidadPersonas;
    private Integer cantidadCamas;
    private Integer cantidadBaños;

    @ManyToOne
    private HotelEntity hotel;

}
