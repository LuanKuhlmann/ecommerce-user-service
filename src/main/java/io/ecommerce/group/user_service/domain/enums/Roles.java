package io.ecommerce.group.user_service.domain.enums;

public enum Roles {
    ADM("system_admnistrator"),
    USER("system_user"),
    COURIER("courier");

    private final String name;

    Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
