package com.sda.OnlineShopMD.mapper;

import com.sda.OnlineShopMD.dto.UserAccountDTO;
import com.sda.OnlineShopMD.entities.UserAccount;
import com.sda.OnlineShopMD.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAccount map(UserAccountDTO userAccountDTO){
        return UserAccount.builder()
                .email(userAccountDTO.getEmail())                                         //il luam din useraccountdto
                .address(userAccountDTO.getAddress())
                .fullName(userAccountDTO.getFullName())
                .password(bCryptPasswordEncoder.encode(userAccountDTO.getPassword()))
                .build();
    }
}
