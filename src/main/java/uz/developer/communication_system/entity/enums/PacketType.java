package uz.developer.communication_system.entity.enums;

public enum PacketType {

//    NET_LIMIT_ALL,
//    NET_LIMIT_TELEGRAM,
//    NET_LIMIT_YOUTUBE,
//    SMS,
//    MINUTE_OUT_NET,
//    MINUTE_IN_NET;



    NET_LIMIT_ALL("netLimitAll","mb"),
    NET_LIMIT_TELEGRAM("netLimitTelegram","mb"),
    NET_LIMIT_YOUTUBE("netLimitYoutube","mb"),
    SMS("sms","sms"),
    MINUTE_OUT_NET("minuteOutNet","minute"),
    MINUTE_IN_NET("minuteInNet","minute");

    String  name;
    String  birligi;

    PacketType(String name, String birligi) {
        this.name = name;
        this.birligi = birligi;
    }
}
