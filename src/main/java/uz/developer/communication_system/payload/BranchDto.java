package uz.developer.communication_system.payload;

import lombok.Data;

@Data
public class BranchDto {

    private String name;

    private boolean active;

    private Integer districtId;

}
