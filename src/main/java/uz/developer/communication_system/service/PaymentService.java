package uz.developer.communication_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.Payment;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.BranchRepository;
import uz.developer.communication_system.repository.PaymentRepository;
import uz.developer.communication_system.repository.SimCardRepository;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    SimCardRepository simCardRepository;

    public ApiResponse pay(Payment payment){

                Optional<SimCard> optionalSimCard =
        simCardRepository.findByCompanyCodeAndNumber(payment.getCompanyCode(), payment.getNumber());
        if (optionalSimCard.isEmpty()) {
            paymentRepository.save(payment);
            return new ApiResponse("not found this simCard", false);
        }
        SimCard simCard = optionalSimCard.get();
        if (!simCard.isActive() || simCard.isBlock()) {
            paymentRepository.save(payment);
            return new ApiResponse("not found this simCard", false);
        }
        simCard.setBalance(simCard.getBalance() + payment.getAmount());

        payment.setState(true);
        paymentRepository.save(payment);
        simCardRepository.save(simCard);
        return new ApiResponse("Payment success",true);

    }

    public ApiResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Payment> pages = paymentRepository.findAll( pageable);
        return new ApiResponse("success",true,pages);

    }

    public ApiResponse getByStateTrue(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Payment> pages = paymentRepository.findAllByStateTrue( pageable);
        return new ApiResponse("success",true,pages);

    }

    public ApiResponse getByStateFalse(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Payment> pages = paymentRepository.findAllByStateFalse( pageable);
        return new ApiResponse("success",true,pages);

    }

    public ApiResponse getById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);

        return optionalPayment.map(payment -> new ApiResponse(
                "success", true, payment)).orElseGet(() -> new ApiResponse("not found", false));

    }



}
