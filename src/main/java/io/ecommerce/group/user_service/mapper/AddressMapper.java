package io.ecommerce.group.user_service.mapper;

import io.ecommerce.group.user_service.domain.dto.request.RegisterAddressRequest;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredAddressResponse;
import io.ecommerce.group.user_service.domain.entity.Address;

import java.util.List;

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

    public static RegisteredAddressResponse fromAddress(Address address) {
        return new RegisteredAddressResponse(
                address.getId().toString(),
                address.getStreet(),
                address.getNumber(),
                address.getDistrict(),
                address.getCity(),
                address.getState(),
                address.getAddressType(),
                address.getPrincipal(),
                address.getCreatedAt()
        );
    }

    public static List<RegisteredAddressResponse> toRegisteredAddressResponseList(List<Address> addresses) {
        return addresses.stream().map(AddressMapper::fromAddress).toList();
    }
}
