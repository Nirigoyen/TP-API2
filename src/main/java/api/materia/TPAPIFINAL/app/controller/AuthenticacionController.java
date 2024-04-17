package api.materia.TPAPIFINAL.app.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.materia.TPAPIFINAL.app.model.dto.UserLoginDTO;
import api.materia.TPAPIFINAL.app.model.dto.UserRegisterDTO;
import api.materia.TPAPIFINAL.app.model.entity.UserLogin;
import api.materia.TPAPIFINAL.app.service.IUserLoginService;

import javax.crypto.SecretKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticacionController {

    private final int EXPIRATION_TIME_IN_MIN = 60;

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private SecretKey secretKey;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> login (@RequestBody UserLoginDTO credentials){
        if (userLoginService.findUser(credentials.getUsername(), credentials.getPassword()) != null){
            String token = Jwts.builder()
                    .setSubject(credentials.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
            //System.out.println(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Credenciales Invalidas", HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO)
    {
    	if(!userLoginService.userExists(userRegisterDTO.getUsername()))
    	{
    		
    		UserLogin credentials = userLoginService.createUserLoginByRegistering(userRegisterDTO);
    		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
    		Boolean linkeoCorrecto = userLoginService.link2departamento(userRegisterDTO, credentials);
    		userLoginService.save(credentials);
    		
    		if(linkeoCorrecto) {
    			String mensaje = "Usuario creado y asignado correctamente";
    			return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    		}
    		else
    		{
    			String mensaje = "Usuario creado, pero el departamento no existe o ya tiene dueño/inquilino";
    			return new ResponseEntity<>(mensaje, HttpStatus.PARTIAL_CONTENT);
    		}
    	}
    	else {
			return new ResponseEntity<>("Nombre de usuario no disponible", HttpStatus.NOT_ACCEPTABLE);
		}
    		
    }
    
    @GetMapping(value = "/userLogin/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username)
    {
    	UserLogin userLogin = userLoginService.findUserByUsername(username);
    	if(userLogin == null)
		{
			String mensaje = "Usuario no encontrado con nombre: " + username;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
    	
		UserLoginDTO userLoginDTO = userLogin.toDTO();
		return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
    }
    @GetMapping(value = "userLogin/id/{userLoginId}")
    public ResponseEntity<?> findById(@PathVariable int userLoginId)
    {
    	UserLogin userLogin = userLoginService.findById(userLoginId);
    	if(userLogin == null)
		{
			String mensaje = "Usuario no encontrado con ID: " + userLoginId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
    	UserLoginDTO userLoginDTO = userLogin.toDTO();
    	return new ResponseEntity<>(userLoginDTO, HttpStatus.OK);
    }
    
    @GetMapping(value = "userLogin")
    public List<UserLoginDTO> findAll()
    {
    	List<UserLogin> userLogins = userLoginService.findAll();
    	List<UserLoginDTO> userLoginDTOs = new ArrayList<UserLoginDTO>();
    	for(UserLogin UL : userLogins)
    	{
    		userLoginDTOs.add(UL.toDTO());
    	}
    	return userLoginDTOs;
    }
    @DeleteMapping(value = "userLogin/{userLoginId}")
    public ResponseEntity<?> deleteUserLogin(@PathVariable int userLoginId)
    {
    	UserLogin userLogin = userLoginService.findById(userLoginId);
    	if(userLogin == null)
    	{
    		String mensaje = "Usuario no encontrado con ID: " + userLoginId;
    		return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    	}
    	userLoginService.deleteById(userLoginId);
    	String mensaje = "Usuario eliminado con ID: " + userLoginId;
    	return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);
    }
    
    @PutMapping(value = "userLogin/{userLoginId}")
    public ResponseEntity<?> updateUserLogin(@PathVariable int userLoginId, @RequestBody UserLoginDTO userLoginDTO)
    {
    	UserLogin userLoginViejo = userLoginService.findById(userLoginId);
    	if(userLoginViejo == null)
    	{
    		String mensaje = "Usuario no encontrado con ID: " + userLoginId;
    		return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    	}
    	boolean updateExitoso = userLoginService.update(userLoginId, userLoginDTO);
    	if(!updateExitoso)
    	{
    		String mensaje = "El nombre de usuario ya existe, o no se encontro el usuario con ID: " + userLoginId;
    		return new ResponseEntity<>(mensaje, HttpStatus.NOT_ACCEPTABLE);
    	}
    	String mensaje = "El cambio ha sido realizado con exito";
    	return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
    
}
