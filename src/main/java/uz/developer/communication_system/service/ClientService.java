package uz.developer.communication_system.service;


import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.payload.ApiResponse;

@Service
public class ClientService {


    public ApiResponse getBalance(SimCard simCard) {
        return new ApiResponse("successfully",true,simCard.getBalance());
    }
}
