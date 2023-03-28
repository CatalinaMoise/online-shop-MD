package com.sda.OnlineShopMD.service;

import com.sda.OnlineShopMD.dto.UserAccountDTO;
import com.sda.OnlineShopMD.entities.UserAccount;
import com.sda.OnlineShopMD.mapper.UserAccountMapper;
import com.sda.OnlineShopMD.repository.UserAccountRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired //cu adnotarea asta il injectam
    private UserAccountMapper userAccountMapper;

    @Autowired
    private UserAccountRepository userAccountRepository;
    public void addUserAccount(UserAccountDTO userAccountDTO){
        UserAccount userAccount = userAccountMapper.map(userAccountDTO);

        userAccountRepository.save(userAccount);
    }
}
