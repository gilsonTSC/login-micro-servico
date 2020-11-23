package com.br.myfood.login.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.br.myfood.login.entity.Login;
import com.br.myfood.login.repository.LoginRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final LoginRepository loginRepository;
	
	@Autowired
	public CustomAuthenticationProvider(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName();
        // You can get the password here
        String password = authentication.getCredentials().toString();

        System.out.println("XXX Authenticate: " + authentication);
        
        Optional<Login> login = this.loginRepository.findByEmailAndPassword(email, password);
        if(login.isPresent()) {
        	// Your custom authentication logic here
        	return new UsernamePasswordAuthenticationToken(email, password);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}