package com.reddit.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> emailOptional = usersRepository.findByEmailIgnoreCase(email);
        if(emailOptional.isEmpty()) throw new UsernameNotFoundException("email not founded!" + email);
        return new SecurityUser(emailOptional.get());
    }
}
