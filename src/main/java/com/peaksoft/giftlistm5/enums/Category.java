package com.peaksoft.giftlistm5.enums;

public class Category {
    public enum Electronics {
        PHONE, AUDIO, PHOTO_AND_VIDEO_CAM,
        AUTO, TV_AND_VIDEO, PC_LAPTOPS_TABLET
    }

    public enum Clothes {
        BLOUSE_AND_SHIRT, SKIRT, OVERALL,
        SWIMWEAR, DRESS, JUMPERS_AND_CARDIGANS,
        // выше жен ниже комби
        T_SHIRT_AND_POLO, TROUSER,
        SHIRT, SHORT, SWEATSHIRT_AND_HOODIE,
        JACKET_AND_BLAZER, SPORTSWEAR,
        SWIMMING_SHORT, VEST
    }

    public enum School {
        BAG, BOOK, SCHOOL_UNIFORM, STATIONARY
    }

    public enum HomeAndGarden {
        INSTRUMENT, FLOWER, HOME_FURNITURE,
        KITCHEN_FURNITURE, BEDROOM_STUFF
    }

    public enum Shoes {
        CLASSIC, SPORT, SUMMER, WINTER, SNEAKERS
    }

    public enum Transport {
        BMW, MERCEDES, LADA, TOYOTA, HYUNDAI,
        KIA, NISSAN, CHEVROLET, MITSUBISHI
    }
}
