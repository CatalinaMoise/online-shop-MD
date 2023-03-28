package com.sda.OnlineShopMD.validator;

import com.sda.OnlineShopMD.dto.UserAccountDTO;
import com.sda.OnlineShopMD.entities.UserAccount;
import com.sda.OnlineShopMD.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Component
public class UserAccountValidator {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void validate(UserAccountDTO userAccountDTO, BindingResult bindingResult) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findByEmail((userAccountDTO.getEmail()));
        if (optionalUserAccount.isPresent()) {
            FieldError fieldError = new FieldError("userAccountDTO", "email", "ERROR");
            bindingResult.addError(fieldError);
        }
    }
}


