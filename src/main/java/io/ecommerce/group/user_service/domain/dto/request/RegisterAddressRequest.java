package io.ecommerce.group.user_service.domain.dto.request;

import io.ecommerce.group.user_service.domain.enums.AddressType;
import io.ecommerce.group.user_service.domain.enums.BrazilianState;

public record RegisterAddressRequest(
        String street,
        String number,
        String distric,
        String city,
        BrazilianState state,
        AddressType addressType,
        boolean principal
) {
}
