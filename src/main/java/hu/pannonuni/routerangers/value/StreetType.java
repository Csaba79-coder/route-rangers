package hu.pannonuni.routerangers.value;

import lombok.Getter;

@Getter
public enum StreetType {

    ROAD("út"),
    STREET("utca"),
    SQUARE("tér"),
    AVENUE("sor"),
    BOULEVARD("körút"),
    MAIN_SQUARE("főtér"),
    PROMENADE("sétány"),
    ROW("sorok"),
    BANK("part"),
    GARDEN("kert"),
    ALLEY("köz"),
    PUBLIC_PLACE("közterület"),
    DRAIN("dűlő"),
    CIRCLE("kör"),
    BLOCK("kocka"),
    FARM("tanya"),
    PLOT("telek"),
    VILLAGE_END("városvég"),
    OUTER("külső"),
    BRIDGE("híd"),
    PATH("ösvény"),
    CITY("város"),
    CENTER("központ"),
    GREENERY("fásítás"),
    RESORT("üdülő"),
    NICH("zug"),
    STOP("megálló"),
    DOG_PARK("kutyafuttató"),
    UNDERPASS("aluljáró"),
    OVERPASS("felüljáró"),
    TRAFFIC_PATH("kreszpálya"),
    SELF_SUPPLY("önellátó");

    private final String hungarianName;

    StreetType(String hungarianName) {
        this.hungarianName = hungarianName;
    }
}
