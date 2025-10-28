package io.ecommerce.group.user_service.service;

import io.ecommerce.group.user_service.domain.dto.request.RegisterUserRequest;
import io.ecommerce.group.user_service.domain.dto.response.RegisteredUserResponse;
import io.ecommerce.group.user_service.domain.entity.Address;
import io.ecommerce.group.user_service.domain.entity.Role;
import io.ecommerce.group.user_service.domain.entity.SystemUser;
import io.ecommerce.group.user_service.domain.enums.Roles;
import io.ecommerce.group.user_service.mapper.SystemUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SystemUserAggregation {

    private final SystemUserService systemUserService;
    private final AddressService addressService;
    private final RoleService roleService;

    public void registerNewUser(RegisterUserRequest request) {
        SystemUser newUser = systemUserService.registerNewUser(request);
        registerAddress(request, newUser);
        registerRole(newUser);

    }

    private void registerRole(SystemUser newUser) {
        Set<Role> roles = new HashSet<>();
        Role newUserRole = roleService.designRoleToUser(Roles.USER);
        roles.add(newUserRole);
        newUser.setRoles(roles);
    }

    private void registerAddress(RegisterUserRequest request, SystemUser newUser) {
        List<Address> addresses = new ArrayList<>();
        Address newAddress = addressService.registerNewAddress(request.registerAddressRequest());
        addresses.add(newAddress);
        newUser.setAddresses(addresses);
    }
}
