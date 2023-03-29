package com.sda.OnlineShopMD.service;

import com.sda.OnlineShopMD.entities.UserAccount;
import com.sda.OnlineShopMD.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //cauta un user pe baza emailului
        System.out.println("Incerc sa ma loghez cu email-ul " + email );
       Optional<UserAccount> optionalUserAccount = userAccountRepository.findByEmail(email);
        if (optionalUserAccount.isEmpty()){
            throw new UsernameNotFoundException(email);
        }
        UserAccount userAccount = optionalUserAccount.get();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userAccount.getUserRole().name())); //cu name il "transformam in string


        return new User(email, userAccount.getPassword(), roles);
    }
}
