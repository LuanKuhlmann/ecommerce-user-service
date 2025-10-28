package io.ecommerce.group.user_service.domain.enums;

public enum AddressType {
    HOME("home"),
    JOB("job");

    private final String name;

    AddressType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
