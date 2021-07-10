package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public ApiResponse pay(){

    }



}
