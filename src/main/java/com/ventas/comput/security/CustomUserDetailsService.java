package com.ventas.comput.security;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ventas.comput.dao.UsuarioDAO;
import com.ventas.comput.models.Usuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {


	@Autowired
	UsuarioDAO usuarioDao;
	
	
	private  com.ventas.comput.models.Usuario userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		userDetail = usuarioDao.findByEmailId(username);
		
		if(!Objects.isNull(userDetail)) 
			
			
			return  new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
		
		
		else
		   throw new  UsernameNotFoundException("User not found");
	}
	
	
	
	public  com.ventas.comput.models.Usuario getUserDetail(){
		return userDetail;
	}
	

}
