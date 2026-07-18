package net.hectorjpsoares.futuaimod.entity.variant;

public enum FoxVariant {
    VANILLA(0),
    PERRY(1);

    private final int id;

    FoxVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static FoxVariant byId(int id) {
        for (FoxVariant variant : values()) {
            if (variant.id == id) {
                return variant;
            }
        }
        return VANILLA;
    }
}