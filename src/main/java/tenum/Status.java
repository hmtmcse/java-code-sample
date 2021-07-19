package tenum;


public enum Status {

    PENDING((byte) 1, "PENDING"),
    DONE((byte) 2, "DONE"),
    DELETE((byte) 3, "DELETE"),
    INCOMPLETE((byte) 4, "INCOMPLETE"),
    ONGOING((byte) 5, "ONGOING");

    private final Byte key;
    private final String value;

    Status(Byte key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Status get(Byte id) {
        for (Status type : Status.values()) {
            if (type.key.equals(id)) return type;
        }
        return PENDING;
    }

    public static Status get(String name) {
        for (Status type : Status.values()) {
            if (type.name().equals(name) || type.getValue().equals(name)) return type;
        }
        return PENDING;
    }

    public static boolean validate(Byte id) {
        for (Status type : Status.values()) {
            if (type.key.equals(id)) return true;
        }
        return false;
    }

    public static boolean validate(String name) {
        for (Status type : Status.values()) {
            if (type.name().equals(name) || type.getValue().equals(name)) return true;
        }
        return false;
    }

    public static Byte getKey(Byte id) {
        if (id == null) return PENDING.getKey();
        Status type = get(id);
        if (type != null) return type.getKey();
        return PENDING.getKey();
    }

    public static Byte getKey(String name) {
        if (name == null) return PENDING.getKey();
        Status type = get(name);
        if (type != null) return type.getKey();
        return PENDING.getKey();
    }

    public static String getValue(Byte id) {
        if (id == null) return PENDING.getValue();
        Status type = get(id);
        if (type != null) return type.getValue();
        return PENDING.getValue();
    }

    public static String getValue(String name) {
        if (name == null) return PENDING.getValue();
        Status type = get(name);
        if (type != null) return type.getValue();
        return PENDING.getValue();
    }


    public Byte getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
