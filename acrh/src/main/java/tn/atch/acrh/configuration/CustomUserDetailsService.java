package tn.atch.acrh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.atch.acrh.ClientService.model.Client;
import tn.atch.acrh.ClientService.repository.ClientRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
@Autowired
    private ClientRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = userRepository.findUserByEmail(email);
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(client.getEmail())
                        .password(client.getPassword())
                        .roles("USER")
                        .build();

        return userDetails;
    }
}
