package com.escola.escola.security;
import com.escola.escola.model.Usuario;
import com.escola.escola.respository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {
        
        @Autowired
        UsuarioRepository uRepository;

        @Override
        public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

            Usuario user = uRepository.findByLogin(login);

            if(user == null){
                throw new UsernameNotFoundException("Usuário não encontrado!");
            }

            //return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
            return user;
            
        }
}
