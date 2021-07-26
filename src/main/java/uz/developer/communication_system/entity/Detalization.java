package uz.developer.communication_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String type;    //jaraoyon nomi
    private double price;
    private String myCompanyCode;
    private String myNumber;
    private double length;
    private String outCompanyCode;
    private String outNumber;
    private boolean input;     //kiruvchi
    private  boolean output;   //chiquvchi
     private  String  state;   // holati
}
