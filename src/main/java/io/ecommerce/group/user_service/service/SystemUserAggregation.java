package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.dto.request.RegisterAddressRequest;
import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredAddressResponse;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredUserResponse;
import io.ecommerce.group.user_service.domain.entity.Address;
import io.ecommerce.group.user_service.domain.entity.Role;
import io.ecommerce.group.user_service.domain.entity.SystemUser;
import io.ecommerce.group.user_service.domain.enums.Roles;
import io.ecommerce.group.user_service.mapper.AddressMapper;
import io.ecommerce.group.user_service.mapper.SystemUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SystemUserAggregation {

    private final SystemUserService systemUserService;
    private final AddressService addressService;
    private final RoleService roleService;

    public RegisteredUserResponse registerNewUser(RegisterUserRequest request) {
        SystemUser newUser = systemUserService.registerNewUser(request);
        registerAddress(newUser.getId().toString(), request.registerAddressRequest());
        registerRole(newUser, Roles.USER);
        return SystemUserMapper.fromSystemUser(newUser);
    }

    public RegisteredUserResponse registerNewCourier(RegisterUserRequest request) {
        SystemUser newUser = systemUserService.registerNewUser(request);
        registerAddress(newUser.getId().toString(), request.registerAddressRequest());
        registerRole(newUser, Roles.COURIER);
        return SystemUserMapper.fromSystemUser(newUser);
    }

    public void registerRole(SystemUser newUser, Roles role) {
        Set<Role> roles = new HashSet<>();
        Role newUserRole = roleService.designRoleToUser(role);
        roles.add(newUserRole);
        newUser.setRoles(roles);
    }

    public RegisteredAddressResponse registerAddress(String uuid, RegisterAddressRequest request) {
        SystemUser registeredUser = systemUserService.findById(uuid);
        List<Address> addresses =  registeredUser.getAddresses();
        Address newAddress = addressService.registerNewAddress(request);
        addresses.add(newAddress);
        return AddressMapper.fromAddress(newAddress);
    }
}
