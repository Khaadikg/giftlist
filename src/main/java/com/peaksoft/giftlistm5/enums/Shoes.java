package com.peaksoft.giftlistm5.enums;

public enum Shoes {
    THIRTY_FIVE(35),
    THIRTY_SIX(36),
    THIRTY_SEVEN(37),
    THIRTY_EIGHT(38),
    THIRTY_NINE(39),
    FORTY(40),
    FORTY_ONE(41),
    FORTY_TWO(42),
    FORTY_THREE(43),
    FORTY_FOUR(44);
    private int numVal;
    Shoes(int numVal) {
        this.numVal = numVal;
    }
    public int getNumVal() {
        return numVal;
    }
}
