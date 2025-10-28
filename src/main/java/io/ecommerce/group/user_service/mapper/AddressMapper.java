package io.ecommerce.group.user_service.mapper;

import io.ecommerce.group.user_service.domain.dto.request.RegisterAddressRequest;
import io.ecommerce.group.user_service.domain.entity.Address;

public class AddressMapper {

    public static Address fromRegisterAddressRequest(RegisterAddressRequest request) {
        return new Address(
                request.street(),
                request.number(),
                request.distric(),
                request.city(),
                request.state().getNome(),
                request.addressType().getName(),
                request.principal()
                );
    }
}
