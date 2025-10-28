package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.dto.request.RegisterAddressRequest;
import io.ecommerce.group.user_service.domain.entity.Address;
import io.ecommerce.group.user_service.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    public Address registerNewAddress(RegisterAddressRequest request) {
        return AddressMapper.fromRegisterAddressRequest(request);
    }
}
