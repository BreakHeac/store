package com.gegehydro.store.security;

import com.gegehydro.store.dao.UserDao;
import com.gegehydro.store.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * 用户验证方法
 *
 * @author sunhao
 * Create on 2017/12/9
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public JwtUserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userDao.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return new JwtUser(users.getEmail(), users.getPassword(), users.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
    }
}
