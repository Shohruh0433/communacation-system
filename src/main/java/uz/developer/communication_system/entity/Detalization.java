package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.developer.communication_system.entity.enums.DetalizatsionType;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Detalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp date;
    private DetalizatsionType detalizatsionType;    //jaraoyon nomi
    private double price;
    @ManyToOne
    private SimCard currentSimCard;
    private double amount;
    @ManyToOne
    private SimCard secondSimCard;
    private boolean input;     //kiruvchi
    private  boolean output;   //chiquvchi
     private  boolean  success;   // holati
}
