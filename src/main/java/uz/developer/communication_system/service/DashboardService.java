package uz.developer.communication_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.communication_system.entity.PacketTraffic;
import uz.developer.communication_system.entity.SimCard;
import uz.developer.communication_system.entity.TariffTraffic;
import uz.developer.communication_system.payload.ApiResponse;
import uz.developer.communication_system.repository.PacketTrafficRepository;
import uz.developer.communication_system.repository.SimCardRepository;
import uz.developer.communication_system.repository.TariffTrafficRepository;

import java.util.List;

@Service
public class DashboardService {

  private  final TariffTrafficRepository tariffTrafficRepository;
  private final PacketTrafficRepository packetTrafficRepository;
  private final SimCardRepository simCardRepository;
  @Autowired
    public DashboardService(TariffTrafficRepository tariffTrafficRepository, PacketTrafficRepository packetTrafficRepository, SimCardRepository simCardRepository) {
        this.tariffTrafficRepository = tariffTrafficRepository;
      this.packetTrafficRepository = packetTrafficRepository;
    this.simCardRepository = simCardRepository;
  }

  public ApiResponse getByPacketTrafficForPacketIdAndActive(Integer packetId){
    List<PacketTraffic> packetTrafficList = packetTrafficRepository.findAllByPacketIdAndActiveIsTrue(packetId);
    return new ApiResponse("result",true,packetTrafficList);
  }

  public ApiResponse getByTariffTrafficForTariffIdAndActive(Long tariffId){
    List<TariffTraffic> tariffTrafficList = tariffTrafficRepository.findAllByTariffIdAndActiveIsTrue(tariffId);
    return new ApiResponse("result",true,tariffTrafficList);
  }

  public ApiResponse findAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(
          Integer branchId,Integer year,Integer month){
    List<SimCard> list = simCardRepository.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_Month(branchId,year,month);
    return new ApiResponse("result",true,list);
  }
  public ApiResponse findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
          Integer branchId,Integer year,Integer month,Integer day){
    List<SimCard> list = simCardRepository.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(branchId,year,month,day);
    return new ApiResponse("result",true,list);
  }
  public ApiResponse findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
          Integer branchId,Integer year,Integer month,Integer day,Integer day2){
    List<SimCard> list = simCardRepository.findAllByBranchIdAndCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(
            branchId,year,month,day,day2);
    return new ApiResponse("result",true,list);
  }

  public ApiResponse findAllByCreatedAt_YearAndCreatedAt_Month(
         Integer year,Integer month){
    List<SimCard> list = simCardRepository.findAllByCreatedAt_YearAndCreatedAt_Month(year,month);
    return new ApiResponse("result",true,list);
  }
  public ApiResponse findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(
          Integer year,Integer month,Integer day){
    List<SimCard> list = simCardRepository.findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_Day(year,month,day);
    return new ApiResponse("result",true,list);
  }
  public ApiResponse findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(
          Integer year,Integer month,Integer day,Integer day2){
    List<SimCard> list = simCardRepository.findAllByCreatedAt_YearAndCreatedAt_MonthAndCreatedAt_DayBetween(
            year,month,day,day2);
    return new ApiResponse("result",true,list);
  }





}
