package com.ventas.comput.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ventas.comput.constans.ComputoConstans;
import com.ventas.comput.dao.UsuarioDAO;
import com.ventas.comput.models.Usuario;
import com.ventas.comput.security.CustomUserDetailsService;
import com.ventas.comput.security.jwt.JWTFilter;
import com.ventas.comput.security.jwt.JWTUtil;
import com.ventas.comput.service.UsuarioService;
import com.ventas.comput.utils.ComputoUtils;
import com.ventas.comput.utils.EmailUtils;
import com.ventas.comput.wrapper.UsuarioWrapper;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	
	
	
	@Autowired
	UsuarioDAO usuarioDao;
	
	
	@Autowired
	private AuthenticationManager authenticationManager ;
	
	
	@Autowired
	private CustomUserDetailsService customerUserDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private JWTFilter jwtFilter;
	
	

	@Autowired
	private EmailUtils emailUtils;
	
	
	@Override
	public ResponseEntity<String> register(Map<String, String> requestMap) {
		
		
		
		try {
			
			
			
	if(validarRegistro(requestMap)) {
				
		
		
				Usuario usuario  =usuarioDao.findByEmailId(requestMap.get("email"));
				
				
				if(Objects.isNull(usuario)) {
					
					usuarioDao.save(getUsuarioFromMap(requestMap));
					
					return ComputoUtils.getResponseEntity("Registro exitoso. ", HttpStatus.OK);
					
				}else {
					return ComputoUtils.getResponseEntity("El email ya existe", HttpStatus.BAD_REQUEST);
				}
				
			}else {
				return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
			}
		
			
			
			
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	
	private boolean validarRegistro(Map<String,String> requestMap) {
		
		if(requestMap.containsKey("nombres") && requestMap.containsKey("telefono") && requestMap.containsKey("email") && requestMap.containsKey("password")) {
			
			return true;
			
		}
		 
		return false;
		
	}
	
	
	
	private Usuario getUsuarioFromMap(Map<String,String > requestMap) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(requestMap.get("nombres"));

		usuario.setTelefono(requestMap.get("telefono"));
		
		usuario.setEmail(requestMap.get("email"));
		
		usuario.setPassword(requestMap.get("password"));
		
		usuario.setEstado("true");
		
		usuario.setRole("usuario");
		
		return usuario;
		
	}


	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		
		try {
			
			Authentication authentication = authenticationManager.authenticate(
					
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
					
					);
			
	
				if(authentication.isAuthenticated()) {
					
					
					String token = jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail(), customerUserDetailsService.getUserDetail().getRole());
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("token", token);
					
					if(customerUserDetailsService.getUserDetail().getEstado().equalsIgnoreCase("true")) {
						
						return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
						
					}else {
						return new ResponseEntity<String>("{\"mensaje\":\""+"Espere la aprobacion del administrador"+"\"}",HttpStatus.BAD_REQUEST);
					}
					
				}
			
		} catch (Exception e) {
			
		}
		
		
		return new ResponseEntity<String>("{\"mensaje\":\""+"Credenciales incorrectas"+"\"}",HttpStatus.BAD_REQUEST);
	}


	@Override
	public ResponseEntity<List<UsuarioWrapper>> getAllUser() {
		
		try {
			
			
			if(jwtFilter.isAdmin()) {
				
				
				return new ResponseEntity<>(usuarioDao.getAllUser(),HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<List<UsuarioWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}


	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		
		
			try {
				
				if(jwtFilter.isAdmin()) {
					
					Optional<Usuario> optional= usuarioDao.findById(Integer.parseInt(requestMap.get("id")));
					
					if(!optional.isEmpty()) {
						
						usuarioDao.updateStatus(requestMap.get("estado"), Integer.parseInt(requestMap.get("id")));
						
						//sendMailToAllAdmin(requestMap.get("estado"),optional.get().getEmail(),usuarioDao.getAllAdmin());
						
						return ComputoUtils.getResponseEntity("Estado de Usuario actualizado", HttpStatus.OK);
						
					}else {
						return ComputoUtils.getResponseEntity(ComputoConstans.UNAUTHORIZED_ACCESS, HttpStatus.OK);
					}
					
				}
				
				return ComputoUtils.getResponseEntity("Usuario no existe", HttpStatus.INTERNAL_SERVER_ERROR);
				
			} catch (Exception e) {
				// TODO: handle exception
					
				
				   e.printStackTrace(); 
			}
			
			return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	private void sendMailToAllAdmin(String estado, String usuario, List<String> allAdmin) {
		
		
		allAdmin.remove(jwtFilter.getCurrentUser());
		
		if(estado != null && estado.equalsIgnoreCase("true")  ) {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Cuenta aprobada", "USUARIO: - "+usuario+" \n es aprobado por \n ADMIN: - " +jwtFilter.getCurrentUser(), allAdmin);
		}else {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Cuenta denegada", "USUARIO: - "+usuario+" \n es denegada por \n ADMIN: - " +jwtFilter.getCurrentUser(), allAdmin);
			
		}
		
		
		
	}


	@Override
	public ResponseEntity<String> checkToken() {
		
		return ComputoUtils.getResponseEntity("true", HttpStatus.OK);
		
		
	}


	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
	
		
		try {
			
			Usuario usuario= usuarioDao.findByEmail(jwtFilter.getCurrentUser());
			
			if(!usuario.equals(null)) {
				
				if(usuario.getPassword().equals(requestMap.get("oldPassword"))) {
					
					usuario.setPassword(requestMap.get("newPassword"));
				
					usuarioDao.save(usuario);
					
					return ComputoUtils.getResponseEntity("Password Actualizado correctamente", HttpStatus.OK);
					
					
					
				}
				
				
				
				return ComputoUtils.getResponseEntity("Es incorrecto la antigua contraseña", HttpStatus.OK);
				
				
			}
			return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		} catch (Exception e) {
			   e.printStackTrace(); 
		}
		
		
		return ComputoUtils.getResponseEntity(ComputoConstans.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	

	
	
}
